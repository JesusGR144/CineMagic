// package com.cinemagic.cinemagic.user;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.cinemagic.cinemagic.exceptions.ApiRequestException;

// @Service
// public class UserService {
    
//     private final UserRepository userRepository;

//     public UserService(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     public List<UserEntity> getAllUsers() {
//         return userRepository.findAll();
//     }

//     public UserEntity getUserById(Long id) {
//         return userRepository.findById(id)
//                 .orElseThrow(() -> new ApiRequestException("User not found with id: " + id));
//     }

//     public UserEntity createUser(UserEntity user) {
//         return userRepository.save(user);
//     }

//     public void deleteUser(Long id) {
//         if (!userRepository.existsById(id)) {
//             throw new ApiRequestException("User not found with id: " + id);
//         }
//         userRepository.deleteById(id);
//     }
// }


package com.cinemagic.cinemagic.user;

import java.util.Set;
import java.util.HashSet;
import org.springframework.stereotype.Service;

import com.cinemagic.cinemagic.exceptions.ApiRequestException;
import com.cinemagic.cinemagic.security.RoleEntity;
import com.cinemagic.cinemagic.security.RoleEnum;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("User not found with id: " + id));
    }

    public UserEntity createUser(UserDTO userDTO) {
        RoleEnum roleEnum = RoleEnum.valueOf(userDTO.getRole());
        
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(roleEnum);
        
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleEntity);
        
        UserEntity user = UserEntity.builder()
            .name(userDTO.getName())
            .password(userDTO.getPassword())
            .email(userDTO.getEmail())
            .accountNotExpired(userDTO.isAccountNotExpired())
            .accountNotLocked(userDTO.isAccountNotLocked())
            .isActive(true)
            .roles(roles) 
            .build();
    
        return userRepository.save(user);
    }
    

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ApiRequestException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
