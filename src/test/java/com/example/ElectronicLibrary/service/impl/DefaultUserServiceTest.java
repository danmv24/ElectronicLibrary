package com.example.ElectronicLibrary.service.impl;

import com.example.ElectronicLibrary.entity.UserEntity;
import com.example.ElectronicLibrary.enums.Role;
import com.example.ElectronicLibrary.exception.RoleNotFoundException;
import com.example.ElectronicLibrary.exception.UserNotFoundException;
import com.example.ElectronicLibrary.repository.UserRepository;
import com.example.ElectronicLibrary.view.UserView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DefaultUserService userService;

    private UserEntity userEntity;

    private List<UserEntity> userEntities;

    private Long id;

    @BeforeEach
    void setUp() {
        userEntities = new ArrayList<>();
        userEntities.add(new UserEntity(1L, "username_one", "password_one", String.valueOf(Role.ROLE_USER)));
        userEntities.add(new UserEntity(2L, "username_two", "password_two", String.valueOf(Role.ROLE_USER)));

        userEntity = UserEntity.builder()
                .id(3L)
                .username("jack")
                .password("strongpassword")
                .role(String.valueOf(Role.ROLE_USER))
                .build();

        id = 3L;
    }

    @Test
    void testFindAllUsers() {
        when(userRepository.findAll()).thenReturn(userEntities);

        List<UserView> userViews = userService.all();

        assertEquals(2, userViews.size());
        assertThat(userViews).extracting(UserView::getId).contains(1L, 2L);
        assertThat(userViews).extracting(UserView::getUsername).contains("username_one", "username_two");
        assertThat(userViews).extracting(UserView::getRole).contains("ROLE_USER", "ROLE_USER");
    }

    @Test
    void testChangeRoleWithValidRole() {
        String newRole = "ROLE_ADMIN";

        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
        userService.changeRole(id, newRole);

        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(userEntity);
        assertEquals(newRole, userEntity.getRole());
    }

    @Test
    void testChangeRoleWithInValidRole() {
        String newInvalidRole = "ROLE_DEVELOPER";

        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
        assertThrows(RoleNotFoundException.class, () -> {
            userService.changeRole(id, newInvalidRole);
        });
        assertEquals("ROLE_USER", userEntity.getRole());
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(0)).save(userEntity);
    }

    @Test
    void testChangeRoleWhenUserNotFound() {
        String newRole = "ROLE_ADMIN";

        when(userRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {
            userService.changeRole(id, newRole);
        });

        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(0)).save(any());
    }
}