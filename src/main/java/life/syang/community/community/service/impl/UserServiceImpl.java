package life.syang.community.community.service.impl;

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
    public User queryById(String accountId) {
        return userMapper.queryById(accountId);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
}
