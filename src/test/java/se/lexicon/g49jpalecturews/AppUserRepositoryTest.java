package se.lexicon.g49jpalecturews;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.g49jpalecturews.entity.*;
import se.lexicon.g49jpalecturews.repository.AppUserRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void findAppUserByUsername() {
        // given
        AppUser appUser = new AppUser("testUser", "testPassword");
        entityManager.persist(appUser);
        entityManager.flush();

        // when
        AppUser found = appUserRepository.findAppUserByUsername(appUser.getUsername());

        // then
        assertEquals(appUser.getUsername(), found.getUsername());
    }

    // Add similar tests for the other methods in AppUserRepository

    @Test
    public void findAppUserByRegDateBetween() {
        // given
        AppUser appUser1 = new AppUser("testUser1", "testPassword1");
        AppUser appUser2 = new AppUser("testUser2", "testPassword2");
        entityManager.persist(appUser1);
        entityManager.persist(appUser2);
        entityManager.flush();

        // when
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(1);
        List<AppUser> foundUsers = appUserRepository.findAppUserByRegDateBetween(startDate, endDate);

        // then
        assertTrue(foundUsers.contains(appUser1));
        assertTrue(foundUsers.contains(appUser2));
    }

    @Test
    public void findAppUserByUserDetails_Id() {
        // given
        Details details = new Details();
        details.setEmail("testEmail@test.com"); // Set a value for the email property
        details.setName("testName"); // Set a value for the name property
        entityManager.persist(details);
        entityManager.flush();

        AppUser appUser = new AppUser("testUser", "testPassword");
        appUser.setUserDetails(details);
        entityManager.persist(appUser);
        entityManager.flush();

        // when
        AppUser found = appUserRepository.findAppUserByUserDetails_Id(details.getId());

        // then
        assertEquals(appUser.getId(), found.getId());
    }

    @Test
    public void findAppUserByUserDetails_EmailIgnoreCase() {
        // given
        Details details = new Details();
        details.setEmail("testEmail@test.com"); // Set a value for the email property
        details.setName("testName"); // Set a value for the name property
        entityManager.persist(details);
        entityManager.flush();

        AppUser appUser = new AppUser("testUser", "testPassword");
        appUser.setUserDetails(details);
        entityManager.persist(appUser);
        entityManager.flush();

        // when
        AppUser found = appUserRepository.findAppUserByUserDetails_EmailIgnoreCase(details.getEmail().toLowerCase());

        // then
        assertEquals(appUser.getUserDetails().getEmail(), found.getUserDetails().getEmail());
    }
}
