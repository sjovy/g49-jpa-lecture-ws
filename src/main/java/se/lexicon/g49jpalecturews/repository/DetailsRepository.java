package se.lexicon.g49jpalecturews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.g49jpalecturews.entity.Details;

@Repository
public interface DetailsRepository extends JpaRepository<Details, String> {

    Details findDetailsByEmail(String email);
    Details findDetailsByNameContains(String name);
    Details findDetailsByNameIgnoreCase(String name);

}
