package com.example.authprofile.service;

import com.example.authprofile.model.UserEntity;
import com.example.authprofile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity user) {
        if (userRepository.findByUsername(user.getUsername()) == null &&
                fieldValid(user)) {
            userRepository.createUser(user);
            return user;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean fieldValid(UserEntity user) {
        boolean phoneIsNumber = true;
        for (Character c : user.getPhoneNumber().toCharArray()) {
            if (!Character.isDigit(c)) {
                phoneIsNumber = false;
                break;
            }
        }
        return !user.getAddress().isEmpty() && phoneIsNumber;
    }

    @Override
    public List<UserEntity> findAll() {
        Iterator<UserEntity> userIterator = userRepository.findAll();
        List<UserEntity> allUser = new ArrayList<>();
        userIterator.forEachRemaining(allUser::add);
        return allUser;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void update(String userId, UserEntity user) {
        userRepository.update(userId, user);
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.delete(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername called");
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("User found");
        return new User(user.getUsername(), user.getPassword(), mapToAuth(user.getType()));
    }

    private Collection<GrantedAuthority> mapToAuth(String type) {
        ArrayList<String> roles = new ArrayList<>();
        roles.add(type);
        return roles.stream().map(role -> new SimpleGrantedAuthority(type)).collect(Collectors.toList());
    }

    public void setUserRepository(UserRepository userRepository2) {
        this.userRepository = userRepository2;
    }
}
