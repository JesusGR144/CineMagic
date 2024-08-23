// package com.cinemagic.cinemagic.user;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     @Autowired
//     private UserService userService;

//     @GetMapping
//     public List<UserEntity> getAllUsers() {
//         return userService.getAllUsers();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
//         UserEntity user = userService.getUserById(id);
//         return new ResponseEntity<>(user, HttpStatus.OK);
//     }

//     @PostMapping
//     public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
//         UserEntity newUser = userService.createUser(user);
//         return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//         userService.deleteUser(id);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }
// }


package com.cinemagic.cinemagic.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        UserEntity user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDTO userDTO) {
        UserEntity newUser = userService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
