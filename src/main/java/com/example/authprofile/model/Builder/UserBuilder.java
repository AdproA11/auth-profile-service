package com.example.authprofile.model.Builder;

import com.example.authprofile.model.Enum.UserType;
import com.example.authprofile.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {
    private UserEntity currentUser;

    public UserBuilder() {
        this.reset();
    }

    public UserBuilder(UserEntity user) {
        this.setCurrent(user);
    }

    public UserBuilder(String name, String username, String password, String address, String phoneNumber, String type) {
        this.reset();
        this.addName(name);
        this.addUsername(username);
        this.addPassword(password);
        this.addAddress(address);
        this.addPhoneNumber(phoneNumber);
        this.addType(type);
    }

    public UserBuilder reset() {
        currentUser = new UserEntity();
        return this;
    }

    public UserBuilder addName(String name) {
        currentUser.setName(name);
        return this;
    }

    public UserBuilder addUsername(String username) {
        currentUser.setUsername(username);
        return this;
    }

    public UserBuilder addPassword(String password) {
        currentUser.setPassword(password);
        return this;
    }

    public UserBuilder addAddress(String address) {
        currentUser.setAddress(address);
        return this;
    }

    public UserBuilder addPhoneNumber(String phoneNumber) {
        currentUser.setPhoneNumber(phoneNumber);
        return this;
    }

    public UserBuilder addType(String type) {
        if (UserType.contains(type)) {
            currentUser.setType(type);
            return this;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public UserBuilder setCurrent(UserEntity user) {
        currentUser = user;
        return this;
    }

    public UserEntity build() {
        return currentUser;
    }

    public UserEntity getCurrent() {
        return currentUser;
    }

    public boolean isBuyer() {
        return UserType.BUYER.name().equals(currentUser.getType());
    }

    public boolean isAdmin() {
        return UserType.ADMIN.name().equals(currentUser.getType());
    }

}
