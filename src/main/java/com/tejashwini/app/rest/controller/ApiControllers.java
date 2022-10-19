package com.Kruthik.app.rest.controller;

import com.Kruthik.app.rest.Models.User;
import com.Kruthik.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage(){
        return "Welcome";
    }

    @GetMapping(value = "/expenses/view")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping(value = "/expenses/add")
    public String saveUsers(@RequestBody User user) {
        userRepo.save(user);
        return "Saved...";
    }

    @PutMapping(value = "/expenses/update/{id}")
    public String updateuser(@PathVariable long id, @RequestBody User user){
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setName(user.getName());
        updatedUser.setTransaction(user.getTransaction());
        updatedUser.setAmount(user.getAmount());
        updatedUser.setDate(user.getDate());
        userRepo.save(updatedUser);
        return "Updated...";
    }

    @DeleteMapping(value = "/expenses/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Delete user with the id: "+id;
    }
}
