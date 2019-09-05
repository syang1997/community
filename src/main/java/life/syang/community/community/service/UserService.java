package life.syang.community.community.service;

import life.syang.community.community.model.User;

public interface UserService {
    User queryByToken(String token);

    User queryById(int id);

    void insertUser(User user);
}
