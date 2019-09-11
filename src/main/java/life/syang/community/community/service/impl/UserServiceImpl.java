package life.syang.community.community.service.impl;

import life.syang.community.community.exception.CustomizeErrorCode;
import life.syang.community.community.exception.CustomizeException;
import life.syang.community.community.mapper.UserMapper;
import life.syang.community.community.model.User;
import life.syang.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryByToken(String token) {
        return userMapper.queryByToken(token);
    }

    @Override
    public User queryById(int id) {
        return userMapper.queryById(id);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User queryByAccountId(String accountId) {
        User user = userMapper.queryByAccountId(accountId);
        if(user==null){
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_LOGIN);
        }
        return user;
    }

    @Override
    public void userLogin(User user) {
        userMapper.updataToken(user);
    }
}
