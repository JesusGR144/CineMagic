// package com.cinemagic.CineMagic.User;


// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.List;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentCaptor;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.cinemagic.cinemagic.user.UserEntity;
// import com.cinemagic.cinemagic.user.UserRepository;
// import com.cinemagic.cinemagic.user.UserService;

// @ExtendWith(MockitoExtension.class)
// public class UserServiceTest {
//     @Mock
//     private UserRepository userRepository;
//     private UserService underTest;

//     @BeforeEach
//     void setUp() {
//         underTest = new UserService(userRepository);
//     }

//     @Test
//     void getUsers() {
//         // Dado
//         UserEntity user1 = new UserEntity("User1", "1234", "user1@mail.com", true, true, true);
//         UserEntity user2 = new UserEntity("User2", "1234", "user2@mail.com", true, true, true);

//         List<UserEntity> users = List.of(user1, user2);
//         when(userRepository.findAll()).thenReturn(users);

//         // Cuando
//         List<UserEntity> result = underTest.getAllUsers();

//         // Entonces
//         verify(userRepository, Mockito.times(1)).findAll();
//         assertEquals(users, result);
//     }

//     @Test
//     void addUser() {
//         // Dado
//         UserEntity user = new UserEntity("Sample User 1", "1234", "samplemail1@mai.com", true, true, true);

//         // Cuando
//         underTest.createUser(user);

//         // Entonces
//         ArgumentCaptor<UserEntity> userArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
//         verify(userRepository).save(userArgumentCaptor.capture());

//         UserEntity capturedUser = userArgumentCaptor.getValue();

//         assertEquals(user, capturedUser);
//     }
// }


package com.cinemagic.CineMagic.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cinemagic.cinemagic.user.UserDTO;
import com.cinemagic.cinemagic.user.UserEntity;
import com.cinemagic.cinemagic.user.UserRepository;
import com.cinemagic.cinemagic.user.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository);
    }

    @Test
    void getUsers() {
        // Dado
        UserEntity user1 = new UserEntity("User1", "1234", "user1@mail.com", true, true, true);
        UserEntity user2 = new UserEntity("User2", "1234", "user2@mail.com", true, true, true);

        List<UserEntity> users = List.of(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Cuando
        List<UserEntity> result = underTest.getAllUsers();

        // Entonces
        verify(userRepository, Mockito.times(1)).findAll();
        assertEquals(users, result);
    }

    @Test
    void addUser() {
        // Dado
        UserDTO userDTO = new UserDTO("Sample User 1", "1234", "samplemail1@mai.com", true, true);

        UserEntity userEntity = UserEntity.builder()
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .accountNotExpired(userDTO.isAccountNotExpired())
                .accountNotLocked(userDTO.isAccountNotLocked())
                .isActive(true) // Por defecto, lo hacemos activo al crear
                .build();

        when(userRepository.save(userEntity)).thenReturn(userEntity);

        // Cuando
        UserEntity result = underTest.createUser(userDTO);

        // Entonces
        ArgumentCaptor<UserEntity> userArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        UserEntity capturedUser = userArgumentCaptor.getValue();

        assertEquals(userEntity, capturedUser);
        assertEquals(userEntity, result);
    }
}
