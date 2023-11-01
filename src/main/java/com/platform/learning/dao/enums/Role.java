package com.platform.learning.dao.enums;

public enum Role {
    USER("Студент"),
    ADMIN("Администратор"),

    TEACHER("Учитель");

    final String description;
    public String getDescription(){
        return description;
    }
    Role(String description) {
        this.description = description;
    }
}
