package com.exemple.CellarApp.Repository.Employe;

import com.exemple.CellarApp.EnumUtils.URLs;
import com.exemple.CellarApp.Model.Castel;
import com.exemple.CellarApp.Model.Employe;
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
import java.util.Optional;

@Repository
public class EmployeRepositoryImpl implements EmployeRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeRepositoryImpl.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Employe> findAll() {
        ObjectMapper mapper = new ObjectMapper();
        List<Employe> listEmploye = new ArrayList<>();
        try {
            listEmploye = mapper.readValue(new URL("file:" + URLs.Employe.url), new TypeReference<>() {
            });
            listEmploye.removeIf(e -> null == e.getUsername() || null == e.getPassword());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return listEmploye;
    }

    public Employe findById(Integer id) {
        List<Employe> employes = findAll();
        for (Employe employe : employes) {
            if (employe.getId().equals(id)) {
                return employe;
            }
        }
        return null;
    }

    public Employe findByUsername(String username) {
        List<Employe> employes = findAll();
        for (Employe employe : employes) {
            if (employe.getUsername().equals(username)) {
                return employe;
            }
        }
        return null;
    }

    public void addNew(Employe employe) {
        List<Employe> list = findAll();
        if (!list.isEmpty()) {
            employe.setId(list.get(list.size() - 1).getId() + 1);
        } else {
            employe.setId(0);
        }
        list.add(employe);
        try {
            mapper.writeValue(new File(URLs.Employe.url), list);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void modify(Integer id, Employe employe) {
        List<Employe> list = findAll();
        if (!list.isEmpty()) {
            for (Employe emp : list) {
                if (emp.getId().equals(id)) {
                    Employe newEmploye = new Employe(emp.getId(),
                            employe.getName(),
                            employe.getFirstName(),
                            employe.getBirthDay(),
                            employe.getUsername(),
                            employe.getPassword());
                    list.removeIf(employe1 -> employe1.getId().equals(id));
                    list.add(newEmploye);
                    break;
                }
            }
            try {
                mapper.writeValue(new File(URLs.Employe.url), list);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public void deleteById(Integer id) {
        List<Employe> employes = findAll();
        employes.removeIf(c -> c.getId().equals(id));
        try {
            mapper.writeValue(new File(URLs.Employe.url), employes);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void deleteAll() {
        try {
            mapper.writeValue(new File(URLs.Employe.url), new ArrayList<Castel>());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
