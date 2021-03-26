package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetTypeMapServiceTest {

	private PetTypeMapService petTypeMapService;
	final Long aLong = 1L;

	@BeforeEach
	void setUp() {
		petTypeMapService = new PetTypeMapService();
		petTypeMapService.save(PetType.builder().id(aLong).build());
	}

	@Test
	void findAll() {
		Set<PetType> petTypes = petTypeMapService.findAll();
		assertEquals(1, petTypes.size());
	}

	@Test
	void deleteById() {
		petTypeMapService.deleteById(aLong);
		assertEquals(0, petTypeMapService.findAll().size());
	}

	@Test
	void delete() {
		petTypeMapService.delete(petTypeMapService.findById(aLong));
		assertEquals(0, petTypeMapService.findAll().size());
	}

	@Test
	void save() {
		Long id = 2L;
		PetType petType2 = PetType.builder().id(id).build();
		PetType savedPetType = petTypeMapService.save(petType2);
		assertEquals(petType2, savedPetType);
		assertEquals(id, savedPetType.getId());
	}

	@Test
	void findById() {
		PetType petType = petTypeMapService.findById(aLong);
		assertEquals(aLong, petType.getId());
	}
}