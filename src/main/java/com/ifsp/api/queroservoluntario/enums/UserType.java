package com.ifsp.api.queroservoluntario.enums;

public enum UserType {
    INDIVIDUAL, COMPANY, MANAGER;

    public static UserType findByName(String name) {
        for (UserType type : UserType.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
