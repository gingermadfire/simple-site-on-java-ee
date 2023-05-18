package com.gingermadfire.javaeesimplesite.exchange.request;

import jakarta.ejb.Stateless;
import jakarta.validation.constraints.NotBlank;

@Stateless
public class UserAuthRequest {

    @NotBlank
    private final String username; //TODO validation

    @NotBlank
    private final String password;

    public UserAuthRequest(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
