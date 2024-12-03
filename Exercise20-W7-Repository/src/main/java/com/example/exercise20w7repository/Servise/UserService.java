package com.example.exercise20w7repository.Servise;

import com.example.exercise20w7repository.Api.ApiException;
import com.example.exercise20w7repository.Model.User;
import com.example.exercise20w7repository.Repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final userRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Integer id,User user) {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("user not found");
        }
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setUserName(user.getUserName());
        u.setPassword(user.getPassword());
        u.setRole(user.getRole());
        u.setAge(user.getAge());
        userRepository.save(u);
    }

    public void deleteUser(Integer id) {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("user not found");
        }
        userRepository.delete(u);
    }

    public void loginUser(Integer id,String username, String password) {
        User u = userRepository.findUserByUserNameAndPassword(id,username,password);
        if (u == null) {
            throw new ApiException("username or password not correct");
        }
    }

    public User getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new ApiException("user not found");
        }
        return user;
    }

    public List<User> getUsersByRole(String role) {
        List<User> users = userRepository.findALLByRole(role);
        if (users == null) {
            throw new ApiException("user not found");
        }
        return users;
    }

    public List<User> getUserByAgeAndAbove(Integer age) {
        List<User> users = userRepository.findAllByAgeAndAbove(age);
        if (users == null) {
            throw new ApiException("user not found");
        }
        return users;
    }
}
