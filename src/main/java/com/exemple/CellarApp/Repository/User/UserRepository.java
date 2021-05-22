package com.exemple.CellarApp.Repository.User;

import com.exemple.CellarApp.DTO.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<UserDTO> findAll();

    UserDTO findById(Integer id);

    UserDTO findByUsername(String username);

    void addNew(UserDTO employe);

    void modify(Integer id, UserDTO employe);

    void deleteById(Integer id);

    void deleteAll();
}
