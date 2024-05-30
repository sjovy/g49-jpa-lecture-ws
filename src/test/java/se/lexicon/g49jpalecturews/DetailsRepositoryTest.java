package se.lexicon.g49jpalecturews;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.g49jpalecturews.entity.Details;
import se.lexicon.g49jpalecturews.repository.DetailsRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class DetailsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DetailsRepository detailsRepository;

    @Test
    public void findDetailsByEmail() {
        // given
        Details details = new Details();
        details.setEmail("testEmail@test.com"); // Set a value for the email property
        details.setName("testName"); // Set a value for the name property
        entityManager.persist(details);
        entityManager.flush();

        // when
        Details found = detailsRepository.findDetailsByEmail(details.getEmail());

        // then
        assertEquals(details.getEmail(), found.getEmail());
    }

    @Test
    public void findDetailsByNameContains() {
        // given
        Details details = new Details();
        details.setEmail("testEmail@test.com"); // Set a value for the email property
        details.setName("testName"); // Set a value for the name property
        entityManager.persist(details);
        entityManager.flush();

        // when
        Details found = detailsRepository.findDetailsByNameContains(details.getName().substring(0, 4));

        // then
        assertEquals(details.getName(), found.getName());
    }

    @Test
    public void findDetailsByNameIgnoreCase() {
        // given
        Details details = new Details();
        details.setEmail("testEmail@test.com"); // Set a value for the email property
        details.setName("testName"); // Set a value for the name property
        entityManager.persist(details);
        entityManager.flush();

        // when
        Details found = detailsRepository.findDetailsByNameIgnoreCase(details.getName().toUpperCase());

        // then
        assertEquals(details.getName(), found.getName());
    }
}
