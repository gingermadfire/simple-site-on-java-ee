package com.gingermadfire.javaeesimplesite.mapper;

import com.gingermadfire.javaeesimplesite.persistence.User;
import jakarta.ejb.Stateless;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Stateless
public class UserRowMapper {

    public Optional<User> map(ResultSet rs) throws SQLException {
        try {
                return Optional.of(new User(
                        rs.getLong("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("cart_id")
                ));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }


}
