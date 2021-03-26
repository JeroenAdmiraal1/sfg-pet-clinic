package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetMapServiceTest {

	private PetMapService petMapService;
	final Long aLong = 1L;

	@BeforeEach
	void setUp() {
		petMapService = new PetMapService();
		petMapService.save(Pet.builder().id(aLong).build());
	}

	@Test
	void findAll() {
		Set<Pet> pets = petMapService.findAll();
		assertEquals(1, pets.size());
	}

	@Test
	void deleteById() {
		petMapService.deleteById(aLong);
		assertEquals(0, petMapService.findAll().size());
	}

	@Test
	void delete() {
		petMapService.delete(petMapService.findById(aLong));
		assertEquals(0, petMapService.findAll().size());
	}

	@Test
	void save() {
		Long id = 2L;
		Pet pet2 = Pet.builder().id(id).build();
		Pet savedPet = petMapService.save(pet2);
		assertEquals(pet2, savedPet);
		assertEquals(id, savedPet.getId());
	}

	@Test
	void findById() {
		Pet pet = petMapService.findById(aLong);
		assertEquals(aLong, pet.getId());
	}
}