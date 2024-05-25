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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity user) {
        return userRepository.save(user);
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
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findById(username);
    }

    @Override
    public void update(String userId, UserEntity user) {
        Optional<UserEntity> userToUpdate = userRepository.findById(userId);
        if (userToUpdate.isPresent()) {
            userToUpdate.get().setName(user.getName());
            userToUpdate.get().setAddress(user.getAddress());
            userRepository.save(userToUpdate.get());
        }
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findById(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }
        return new User(user.get().getUsername(), user.get().getPassword(), mapToAuth(user.get().getType()));
    }

    private Collection<GrantedAuthority> mapToAuth(String type) {
        ArrayList<String> roles = new ArrayList<>();
        roles.add(type);
        return roles.stream().map(role -> new SimpleGrantedAuthority(type)).collect(Collectors.toList());
    }
}
