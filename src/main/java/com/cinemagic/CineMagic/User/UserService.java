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

import org.springframework.stereotype.Service;

import com.cinemagic.cinemagic.exceptions.ApiRequestException;

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
        UserEntity user = UserEntity.builder()
            .name(userDTO.getName())
            .password(userDTO.getPassword())
            .email(userDTO.getEmail())
            .accountNotExpired(userDTO.isAccountNotExpired())
            .accountNotLocked(userDTO.isAccountNotLocked())
            .isActive(true) // Por defecto, lo hacemos activo al crear
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
