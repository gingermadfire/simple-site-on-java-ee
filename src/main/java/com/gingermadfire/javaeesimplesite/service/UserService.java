package com.gingermadfire.javaeesimplesite.service;

import com.gingermadfire.javaeesimplesite.dao.UserDao;
import com.gingermadfire.javaeesimplesite.exception.EntityNotFoundException;
import com.gingermadfire.javaeesimplesite.exception.MismatchContentTypeException;
import com.gingermadfire.javaeesimplesite.exchange.response.UserResponse;
import com.gingermadfire.javaeesimplesite.mapper.UserMapper;
import com.gingermadfire.javaeesimplesite.persistence.User;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Optional;

@Singleton
public class UserService {

    private final Base64.Decoder decoder = Base64.getDecoder();
    @Inject
    private UserDao userDao;
    @Inject
    private UserMapper userMapper;

    public UserResponse authorize(HttpServletRequest request) {
        String basic = request.getHeader("Authorization").split(" ")[1];
        String[] args = new String(decoder.decode(basic)).split(":");
        String username = args[0];
        String password = args[1];
        Optional<User> user = userDao.get(username);

        if (user.isEmpty() || this.passwordCheck(password, user.get().getPassword())) {//TODO: сделать нормальную проверку на пароль
            throw new EntityNotFoundException("Логин или пароль неверны");
        } else {
            return userMapper.map(user.get());
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) {
        if (request.getContentType().equals("application/x-www-form-urlencoded")) {
            Enumeration<String> params = request.getParameterNames();
            User user = new User();

            while (params.hasMoreElements()) {
                String param = params.nextElement();
                switch (param) {
                    case ("firstname") : user.setFirstname(param);
                    case ("lastname") : user.setLastname(param);
                    case ("username") : user.setUsername(param);
                    case ("password") : user.setPassword(param); //TODO: вынести в mapper и если отсутствует какое-либо кинуть ошибку
                }
            }

            userDao.save(user);
        } else {
            throw new MismatchContentTypeException(
                    "Несоответствует формат данных. Требуемый формат \"application/x-www-form-urlencoded\"");
        }
    }

    private boolean passwordCheck(String rawPassword, String hashedPassword) {//что за алгоритм тут используется для хеширования?
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), salt, 65536, 128);

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Arrays.equals(hash, hashedPassword.getBytes());

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Не удалось найти требуемый алгоритм");
        }
    }
}

