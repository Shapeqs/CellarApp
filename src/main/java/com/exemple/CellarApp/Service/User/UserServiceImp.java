package com.exemple.CellarApp.Service.User;

import com.exemple.CellarApp.DTO.UserDTO;
import com.exemple.CellarApp.Model.User;
import com.exemple.CellarApp.Repository.User.UserRepository;
import com.exemple.CellarApp.Service.Bottle.BottleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    /**
     * Le logger de la classe
     */
    private Logger LOGGER = LoggerFactory.getLogger(BottleServiceImpl.class);

    /**
     * Le repository des utilisateurs
     */
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        for (UserDTO e : userRepository.findAll()) {
            users.add(new User(e));
        }
        return users;
    }

    @Override
    public User findOneById(Integer id) {
        UserDTO employe = userRepository.findById(id);
        if (null != employe)
            return new User(employe);
        else
            return null;
    }

    @Override
    public User findOneByLogin(String username) {
        UserDTO employe = userRepository.findByUsername(username);
        if (null != employe)
            return new User(employe);
        else
            return null;
    }

    @Override
    public void deleteOne(Integer id) {
        LOGGER.info("supprimer " + userRepository.findById(id).getUsername());
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void addOne(User e) {
        userRepository.addNew(new UserDTO(e));
    }

    @Override
    public void modifyOne(Integer id, User e) {
        List<User> list = findAll();
        list.removeIf(u -> !u.getId().equals(id));
        if (!list.isEmpty()) {
            userRepository.modify(id, new UserDTO(e));
        }
    }
}
