package com.example.exercise20w7repository.Controller;

import com.example.exercise20w7repository.Api.ApiResponse;
import com.example.exercise20w7repository.Model.User;
import com.example.exercise20w7repository.Servise.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id ,@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("user updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted"));
    }

    @GetMapping("/loginUser/{id}/{userName}/{password}")
    public ResponseEntity loginUser(@PathVariable Integer id,@PathVariable String userName,@PathVariable String password) {
        userService.loginUser(id, userName, password);
        return ResponseEntity.status(200).body(new ApiResponse("user logged in"));
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        User u = userService.getUserByEmail(email);
        if (u == null){
            return ResponseEntity.status(400).body(new ApiResponse("user not found"));
        }
        return ResponseEntity.status(200).body(u);
    }
    @GetMapping("/getUsersByRole/{role}")
    public ResponseEntity  getUsersByRole(@PathVariable String role){
        List<User> users = userService.getUsersByRole(role);
        if (users == null){
            return ResponseEntity.status(400).body(new ApiResponse("user not found"));
        }
        return ResponseEntity.status(200).body(users);
    }
    @GetMapping("/getUsersByAgeAndAbove/{age}")
    public ResponseEntity getUsersByAgeAndAbove(@PathVariable Integer age){
        List<User> users = userService.getUserByAgeAndAbove(age);
        if (users == null){
            return ResponseEntity.status(400).body(new ApiResponse("user not found"));
        }
        return ResponseEntity.status(200).body(users);
    }

}
