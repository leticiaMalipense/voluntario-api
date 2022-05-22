package com.ifsp.api.queroservoluntario.dto;

public class TokenDTO {

    private Long id;
    private String name;
    private String document;
    private String userType;
    private String type;
    private String token;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String document;
        private String userType;
        private String type;
        private String token;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder document(String document) {
            this.document = document;
            return this;
        }

        public Builder userType(String userType) {
            this.userType = userType;
            return this;
        }

        public TokenDTO build() {
            return new TokenDTO(this);
        }

    }

    private TokenDTO(Builder builder) {
        id = builder.id;
        type = builder.type;
        token = builder.token;
        name = builder.name;
        document = builder.document;
        userType = builder.userType;
    }
}
