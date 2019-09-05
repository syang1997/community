package life.syang.community.community.service;

import life.syang.community.community.model.User;

public interface UserService {
    User queryByToken(String token);

    User queryById(String accountId);

    void insertUser(User user);
}
