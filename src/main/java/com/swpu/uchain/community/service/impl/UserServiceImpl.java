package com.swpu.uchain.community.service.impl;

import com.swpu.uchain.community.dao.GroupMapper;
import com.swpu.uchain.community.dao.UserMapper;
import com.swpu.uchain.community.entity.User;
import com.swpu.uchain.community.enums.ResultEnum;
import com.swpu.uchain.community.exception.GlobalException;
import com.swpu.uchain.community.form.LoginForm;
import com.swpu.uchain.community.security.JwtProperties;
import com.swpu.uchain.community.security.JwtUserDetailServiceImpl;
import com.swpu.uchain.community.service.UserService;
import com.swpu.uchain.community.util.JwtTokenUtil;
import com.swpu.uchain.community.util.ResultVOUtil;
import com.swpu.uchain.community.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hobo
 * @description
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User getUserByStuId(String stuId) {
        return userMapper.getUserByStuId(stuId);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public void add(User user) {
        if(userMapper.getUserByStuId(user.getStuId()) != null){
            log.error("该学号用户已存在，user={}",user);
            throw new GlobalException(ResultEnum.USER_ALREADY_EXIST);
        }
        if(groupMapper.selectByPrimaryKey(user.getGroupId()) == null){
            log.error("该小组不存在，groupId={}",user.getGroupId());
            throw new GlobalException(ResultEnum.GROUP_NOT_EXIST);
        }
        userMapper.insert(user);
    }

    @Override
    public void delete(Integer id) {
        if(userMapper.selectByPrimaryKey(id) == null){
            log.error("该用户不存在,id={}",id);
            throw new GlobalException(ResultEnum.USER_NOT_EXIST);
        }
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<User> findByGroupId(Integer id) {

        return userMapper.getUserByGroupId(id);
    }

    @Override
    public void update(User user) {
        if(!userMapper.getUserByStuId(user.getStuId()).getId().equals(user.getId())){
            log.error("该学号用户已存在，user={}",user);
            throw new GlobalException(ResultEnum.USER_ALREADY_EXIST);
        }
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String stuId = authentication.getName();
        String key = "anonymousUser";
        if (!stuId.equals(key)) {
            return getUserByStuId(stuId);
        }
        return null;
    }

    @Override
    public ResultVO login(LoginForm loginForm, HttpServletResponse response) {
        User user = userMapper.getUserByStuId(loginForm.getStuId());
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(loginForm.getStuId());
        if (!(new BCryptPasswordEncoder().matches(loginForm.getPassword(), userDetails.getPassword()))) {
            return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        Authentication token = new UsernamePasswordAuthenticationToken(loginForm.getStuId(), loginForm.getPassword(), userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String realToken = jwtTokenUtil.generateToken(userDetails);
        response.addHeader(jwtProperties.getTokenName(), realToken);


        Map<String, java.io.Serializable> map = new HashMap<>();
        map.put("role", user.getRole());
        map.put("token", realToken);

        return ResultVOUtil.success(map);
    }
}
