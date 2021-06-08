package ru.vlsu.vetclinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import ru.vlsu.vetclinic.persistence.Pet;
import ru.vlsu.vetclinic.persistence.PetRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PetRepositoryTests {

    @Autowired
    private PetRepository repo;

    @Test
    public void testCreatePet(){
    }
}
