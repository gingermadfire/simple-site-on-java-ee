package com.gingermadfire.javaeesimplesite.exchange.response;

import jakarta.ejb.Stateless;

@Stateless
public class UserResponse {

    private String firstname;
    private String lastname;
    private String username;

    public UserResponse(final String firstname, final String lastname, final String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
