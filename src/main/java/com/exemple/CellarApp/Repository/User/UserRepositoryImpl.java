package com.exemple.CellarApp.Repository.User;

import com.exemple.CellarApp.CellarAppApplication;
import com.exemple.CellarApp.DTO.UserDTO;
import com.exemple.CellarApp.EnumUtils.URLs;
import com.exemple.CellarApp.Security.UserRoles;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> findAll() {
        ObjectMapper mapper = new ObjectMapper();
        List<UserDTO> listEmploye = new ArrayList<>();
        try {
            listEmploye = mapper.readValue(new URL("file:" + URLs.User.url), new TypeReference<>() {
            });
            listEmploye.removeIf(e -> null == e.getUsername() || null == e.getPassword());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return listEmploye;
    }

    public UserDTO findById(Integer id) {
        List<UserDTO> employes = findAll();
        for (UserDTO employe : employes) {
            if (employe.getId().equals(id)) {
                return employe;
            }
        }
        return null;
    }

    public UserDTO findByUsername(String username) {
        List<UserDTO> employes = findAll();
        for (UserDTO employe : employes) {
            if (employe.getUsername().equals(username)) {
                return employe;
            }
        }
        return null;
    }

    public void addNew(UserDTO employe) {
        List<UserDTO> list = findAll();
        if (!list.isEmpty()) {
            employe.setId(list.get(list.size() - 1).getId() + 1);
        } else {
            employe.setId(0);
        }
        list.add(employe);
        LOGGER.info(String.format("%s %s with credential (%s:%s) added to app", employe.getRole(), employe.getName(), employe.getUsername(), employe.getPassword()));
        try {
            mapper.writeValue(new File(URLs.User.url), list);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void modify(Integer id, UserDTO employe) {
        List<UserDTO> list = findAll();
        if (!list.isEmpty()) {
            for (UserDTO emp : list) {
                if (emp.getId().equals(id)) {
                    UserDTO newEmploye = new UserDTO(emp.getId(),
                            employe.getName(),
                            employe.getFirstname(),
                            employe.getBirthday(),
                            employe.getUsername(),
                            employe.getPassword(),
                            employe.getRole());
                    list.removeIf(employe1 -> employe1.getId().equals(id));
                    list.add(newEmploye);
                    break;
                }
            }
            try {
                mapper.writeValue(new File(URLs.User.url), list);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public void deleteById(Integer id) {
        List<UserDTO> employes = findAll();
        employes.removeIf(c -> c.getId().equals(id));
        try {
            mapper.writeValue(new File(URLs.User.url), employes);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void deleteAll() {
        List<UserDTO> employes = findAll();
        employes.removeIf(c -> c.getRole().equals(UserRoles.EMPLOYE.name()));
        try {
            mapper.writeValue(new File(URLs.User.url), employes);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
