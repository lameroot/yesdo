package ru.yesdo.service;

import org.springframework.stereotype.Service;
import ru.yesdo.graph.repository.UserGraphRepository;
import ru.yesdo.graph.service.UserGraphService;
import ru.yesdo.model.User;
import ru.yesdo.model.data.UserData;
import ru.yesdo.repository.UserRepository;

import javax.annotation.Resource;

/**
 * Created by lameroot on 18.02.15.
 */
@Service
public class UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserGraphService userGraphService;

    public User create(UserData userData) {
        User user = new User();

        return user;
    }

}
