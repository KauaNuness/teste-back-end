package com.kaua.testebackend.repository;

import com.kaua.testebackend.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should save user successfully")
    void shouldSaveUser() {

        User user = User.builder()
                .name("Kaua")
                .email("kaua@email.com")
                .phone("11999999999")
                .build();

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("Kaua", savedUser.getName());
        assertEquals("kaua@email.com", savedUser.getEmail());
    }

    @Test
    @DisplayName("Should find user by id")
    void shouldFindUserById() {

        User user = User.builder()
                .name("Kaua")
                .email("kaua@email.com")
                .phone("11999999999")
                .build();

        User savedUser = userRepository.save(user);

        Optional<User> foundUser =
                userRepository.findById(savedUser.getId());

        assertTrue(foundUser.isPresent());
        assertEquals("Kaua", foundUser.get().getName());
    }

    @Test
    @DisplayName("Should delete user")
    void shouldDeleteUser() {

        User user = User.builder()
                .name("Kaua")
                .email("kaua@email.com")
                .phone("11999999999")
                .build();

        User savedUser = userRepository.save(user);

        userRepository.delete(savedUser);

        Optional<User> deletedUser =
                userRepository.findById(savedUser.getId());

        assertFalse(deletedUser.isPresent());
    }
}