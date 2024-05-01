package com.example.authprofile.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum UserType {
    BUYER("BUYER"),
    ADMIN("ADMIN");

    private final String value;

    private UserType(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (UserType userType : UserType.values()) {
            if (userType.name().equals(param)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getAll() {
        List<String> types = new ArrayList<>();
        for (UserType userType : UserType.values()) {
            types.add(userType.name());
        }
        return types;
    }

    public String getValue() {
        return value;
    }

    public static UserType fromValue(String value) {
        for (UserType userType : UserType.values()) {
            if (userType.value.equals(value)) {
                return userType;
            }
        }
        return null;
    }

    public static UserType fromName(String name) {
        for (UserType userType : UserType.values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }

    public static boolean isBuyer(String name) {
        return UserType.BUYER.name().equals(name);
    }

    public static boolean isAdmin(String name) {
        return UserType.ADMIN.name().equals(name);
    }

}
