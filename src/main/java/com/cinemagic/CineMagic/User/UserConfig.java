package com.cinemagic.cinemagic.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cinemagic.cinemagic.security.PermissionEntity;
import com.cinemagic.cinemagic.security.RoleEnum;
import com.cinemagic.cinemagic.security.RoleEntity;

import java.util.List;
import java.util.Set;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            PermissionEntity createPermission = PermissionEntity.builder()
                .name("CREATE")
                .build();

            PermissionEntity readPermission = PermissionEntity.builder()
                .name("READ")
                .build();

            PermissionEntity updatePermission = PermissionEntity.builder()
                .name("UPDATE")
                .build();

            PermissionEntity deletePermission = PermissionEntity.builder()
                .name("DELETE")
                .build();

            RoleEntity adminRole = RoleEntity.builder()
                .role(RoleEnum.ADMIN)
                .permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                .build();

            RoleEntity userRole = RoleEntity.builder()
                .role(RoleEnum.VIEWER)
                .permissions(Set.of(readPermission))
                .build();

            RoleEntity managerRole = RoleEntity.builder()
                .role(RoleEnum.MANAGER)
                .permissions(Set.of(createPermission, readPermission, updatePermission))
                .build();
                
            RoleEntity filmStaffRole = RoleEntity.builder()
                .role(RoleEnum.FILM_STAFF)
                .permissions(Set.of(createPermission, readPermission))
                .build();
                
            UserEntity gsus = UserEntity.builder()
                .name("gsus")
                .password("password")
                .email("gsus@example.com")
                .isActive(true)
                .accountNotExpired(true)
                .accountNotLocked(true)
                .roles(Set.of(adminRole, managerRole))
                .build();
                
            UserEntity jesus = UserEntity.builder()
                .name("jesus")
                .password("password")
                .email("jesus@example.com")
                .isActive(true)
                .accountNotExpired(true)
                .accountNotLocked(true)
                .roles(Set.of(userRole))
                .build();
                
            UserEntity tyrone = UserEntity.builder()
                .name("tyrone")
                .password("password")
                .email("tyrone@example.com")
                .isActive(true)
                .accountNotExpired(true)
                .accountNotLocked(true)
                .roles(Set.of(filmStaffRole))
                .build();
            
            userRepository.saveAll(List.of(gsus, jesus, tyrone));
        };
    }
}
