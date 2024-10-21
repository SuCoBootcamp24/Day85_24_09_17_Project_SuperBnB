package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.auth.AuthRegDTO;
import de.supercode.superbnb.dtos.user.UserShortResponseDTO;
import de.supercode.superbnb.entities.person.Role;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.services.AuthentificationService;
import de.supercode.superbnb.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private static AuthentificationService mockAuthenticationService;
    @Mock
    private static UserService mockUserService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void login_ShouldReturnUserDetails() {
        UserShortResponseDTO expectedUser = new UserShortResponseDTO(1L,"John", "Doe", Role.USER, "token");
        when(mockUserService.getUserDetailsByLogin(authentication)).thenReturn(expectedUser);

        UserShortResponseDTO actualUser = authController.login(authentication);

        assertEquals(expectedUser, actualUser);
        verify(mockUserService, times(1)).getUserDetailsByLogin(authentication);
    }

    @Test
    void register_ShouldRegisterUser() {
        // Arrange
        AuthRegDTO authRegDTO = new AuthRegDTO("John", "Doe", "john.doe@example.com", "password123", "123456789", "Street", "42", "12345", "City", "Country", "1234567812345678", "123", YearMonth.of(2025, 12));
        User expectedUser = new User("John", "Doe", "john.doe@example.com", "password123");
        when(mockAuthenticationService.userRegister(authRegDTO)).thenReturn(expectedUser);

        User actualUser = authController.register(authRegDTO);

        assertEquals(expectedUser, actualUser);
        verify(mockAuthenticationService, times(1)).userRegister(authRegDTO);
    }

    @Test
    void forgotPassword() {
    }
}