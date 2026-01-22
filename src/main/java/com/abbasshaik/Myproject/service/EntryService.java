package com.abbasshaik.Myproject.service;

import com.abbasshaik.Myproject.entities.Entry;
import com.abbasshaik.Myproject.entities.User;
import com.abbasshaik.Myproject.repository.EntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EntryService {
    @Autowired
    private EntryRepository EntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(Entry Entry, String username){
        try {
            User user = userService.findByUsername(username);
            Entry.setDate(LocalDateTime.now());
            Entry saved = EntryRepository.save(Entry);
            user.getEntries().add(saved);
            userService.saveUser(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An ERROR has been occured while saving the Entry",e);
        }

    }
    public void saveEntry(Entry Entry){
        EntryRepository.save(Entry);
    }
    public List<Entry> getAll(){
        return EntryRepository.findAll();
    }
    public Optional<Entry> findId(ObjectId id){
        return EntryRepository.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id, String username){
        boolean removed = false;
        User user = userService.findByUsername(username);
        removed =  user.getEntries().removeIf(x -> x.getId().equals(id));
        try {
            if (removed){
                userService.saveUser(user);
                EntryRepository.deleteById(id);
            }
        }catch (Exception e){
            log.error("ERROR", e);
            throw new RuntimeException("An ERROR occured while deleting the entry", e);
        }
        return removed;
    }

}
