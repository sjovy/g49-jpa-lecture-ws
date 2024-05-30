package se.lexicon.g49jpalecturews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g49jpalecturews.entity.AppUser;
import se.lexicon.g49jpalecturews.repository.AppUserRepository;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public void deleteUserById(int id) {
        appUserRepository.deleteById(id);
    }

    public AppUser updateUser(int id, String newUsername) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("AppUser not found"));
        appUser.setUsername(newUsername);
        appUser = appUserRepository.save(appUser); // Save the updated user and reassign it to the appUser variable
        return appUser; // Return the updated user
    }
}