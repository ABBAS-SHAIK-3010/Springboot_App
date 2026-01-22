package com.abbasshaik.Myproject.service;

import com.abbasshaik.Myproject.entities.User;
import com.abbasshaik.Myproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userrepository;

    private static final PasswordEncoder passwordencoder = new BCryptPasswordEncoder();
    public void saveUser(User user){
        userrepository.save(user);
    }
    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordencoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userrepository.save(user);
            return true;
        }catch(Exception e){
            log.info("Its a Error");
            return false;
        }

    }
    public void saveAdmin(User user){
        user.setPassword(passwordencoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userrepository.save(user);
    }
    public List<User> getAll(){
        return userrepository.findAll();
    }
    public Optional<User> findId(ObjectId id){
        return userrepository.findById(id);
    }
    public void deleteById(ObjectId id){
        userrepository.deleteById(id);
    }
    public User findByUsername(String username){
        return userrepository.findByUsername(username);
    }
}
