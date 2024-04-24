package com.example.authprofile.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum UserType {
    BUYER("BUYER"),
    ADMIN("ADMIN");

    private UserType(String value) {
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

}
