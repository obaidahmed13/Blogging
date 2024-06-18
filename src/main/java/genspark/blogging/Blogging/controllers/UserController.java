package genspark.blogging.Blogging.controllers;


import genspark.blogging.Blogging.models.User;
import genspark.blogging.Blogging.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userServ;

    @PostMapping("")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User result = userServ.createUser(user);
        if (result.getId() > 0){
            return ResponseEntity.ok("USer Was Saved");
        }
        return ResponseEntity.status(404).body("Error, USer Not Saved");
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userServ.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('NORMAL')")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        Optional<User> oneUser = userServ.getUserById(id);
        if (oneUser.isPresent()) {
            return ResponseEntity.ok(oneUser);
        }
        return ResponseEntity.status(404).body("Error, User Not found");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userServ.deleteUser(id));
    }


}
