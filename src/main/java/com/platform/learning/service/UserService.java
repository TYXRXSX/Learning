package com.platform.learning.service;

import com.platform.learning.dao.Users;
import com.platform.learning.dao.enums.Role;
import com.platform.learning.repo.AddressRepo;
import com.platform.learning.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepo addressRepo;

    public Users registration(Users user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            return null;
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setRoles(Collections.singleton(Role.ADMIN));
        addressRepo.save(user.getAddress());
        return userRepo.save(user);
    }
    public Users findByUsername(String username){
        return userRepo.findByUsername(username).get();
    }
    public Iterable<Users> getAll(){
        return userRepo.findAll();
    }

    public Users getById(Long id){
        return userRepo.findById(id).get();
    }

    public void delete(Long id){
        userRepo.deleteById(id);
    }
    public Users edit(Users user){
        return userRepo.save(user);
    }
    public List<Users> findAllByRole(Role role){
        return userRepo.findAllByRoles(role);
    }
}
