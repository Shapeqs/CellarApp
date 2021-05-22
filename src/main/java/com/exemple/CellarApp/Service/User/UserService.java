package com.exemple.CellarApp.Service.User;

import com.exemple.CellarApp.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll();

    User findOneById(Integer id);

    User findOneByLogin(String username);

    void deleteOne(Integer id);

    void deleteAll();

    void addOne(User e);

    void modifyOne(Integer id, User e);
}
