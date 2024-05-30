package se.lexicon.g49jpalecturews;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.lexicon.g49jpalecturews.entity.AppUser;
import se.lexicon.g49jpalecturews.repository.AppUserRepository;
import se.lexicon.g49jpalecturews.service.AppUserService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AppUserServiceTest {

    @Mock
    AppUserRepository appUserRepository;

    @InjectMocks
    AppUserService appUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteUserById() {
        int id = 9999;

        appUserService.deleteUserById(id);

        verify(appUserRepository, times(1)).deleteById(id);
    }

    @Test
    void updateUser() {
        int id = 9999;
        String newUsername = "newUsername";
        AppUser appUser = new AppUser();
        appUser.setId(id);
        appUser.setUsername("oldUsername");

        when(appUserRepository.findById(id)).thenReturn(Optional.of(appUser));
        when(appUserRepository.save(any(AppUser.class))).thenReturn(appUser); // Add this line

        AppUser updatedAppUser = appUserService.updateUser(id, newUsername);

        verify(appUserRepository, times(1)).save(appUser);
        assertEquals(newUsername, updatedAppUser.getUsername());
    }
}
