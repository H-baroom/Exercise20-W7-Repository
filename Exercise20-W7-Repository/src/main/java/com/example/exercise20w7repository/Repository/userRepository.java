package com.example.exercise20w7repository.Repository;

import com.example.exercise20w7repository.Model.User;
import org.apache.catalina.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface userRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);

    @Query("select u from User u where u.id =?1 and u.userName =?2 and u.password =?3")
    User findUserByUserNameAndPassword(Integer id, String userName, String password);

    @Query("select u from User u where u.email =?1")
    User getUserByEmail(String email);

    List<User> findALLByRole(String role);

    @Query("select u from User u where u.age >=?1")
    List<User> findAllByAgeAndAbove(Integer age);

}
