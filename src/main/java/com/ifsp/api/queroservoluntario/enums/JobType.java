package com.ifsp.api.queroservoluntario.enums;

public enum JobType {
    SINGLE, RECURRENT;

    public static JobType findByName(String name) {
        for (JobType type : JobType.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
