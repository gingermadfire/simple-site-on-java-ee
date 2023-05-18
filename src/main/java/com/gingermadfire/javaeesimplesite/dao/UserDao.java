package com.gingermadfire.javaeesimplesite.dao;

import com.gingermadfire.javaeesimplesite.config.DatabaseConnectionConfig;
import com.gingermadfire.javaeesimplesite.mapper.UserRowMapper;
import com.gingermadfire.javaeesimplesite.persistence.User;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Singleton
public class UserDao {

    private static final String SELECT_QUERY = "select * from users u where u.username = ?";
    private static final String INSERT_QUERY = "insert into users u values(?,?,?,?,?)";
    @Inject
    private UserRowMapper userRowMapper;

    public Optional<User> get(String username) {
        try (PreparedStatement statement = DatabaseConnectionConfig.getConnection().prepareStatement(SELECT_QUERY)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            return userRowMapper.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка в UserDao"); //TODO
        }
    }

    public void save(User user) {
        try (PreparedStatement statement = DatabaseConnectionConfig.getConnection().prepareStatement(INSERT_QUERY)){
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getCartId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
