package com.gingermadfire.javaeesimplesite.mapper;

import com.gingermadfire.javaeesimplesite.exchange.response.UserResponse;
import com.gingermadfire.javaeesimplesite.persistence.User;
import jakarta.ejb.Stateless;

@Stateless
public class UserMapper {

    public UserResponse map(User user) {
        return new UserResponse(
                user.getFirstname(),
                user.getLastname(),
                user.getUsername()
        );
    }

}
