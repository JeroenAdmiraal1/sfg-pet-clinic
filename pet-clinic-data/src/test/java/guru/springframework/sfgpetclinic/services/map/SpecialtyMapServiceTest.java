package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecialtyMapServiceTest {

	private SpecialtyMapService specialtyMapService;
	final Long aLong = 1L;

	@BeforeEach
	void setUp() {
		specialtyMapService = new SpecialtyMapService();
		specialtyMapService.save(Specialty.builder().id(aLong).build());
	}

	@Test
	void findAll() {
		Set<Specialty> specialties = specialtyMapService.findAll();
		assertEquals(1, specialties.size());
	}

	@Test
	void deleteById() {
		specialtyMapService.deleteById(aLong);
		assertEquals(0, specialtyMapService.findAll().size());
	}

	@Test
	void delete() {
		specialtyMapService.delete(specialtyMapService.findById(aLong));
		assertEquals(0, specialtyMapService.findAll().size());
	}

	@Test
	void save() {
		Long id = 2L;
		Specialty specialty = Specialty.builder().id(id).build();
		Specialty savedSpecialty = specialtyMapService.save(specialty);
		assertEquals(specialty, savedSpecialty);
		assertEquals(id, savedSpecialty.getId());
	}

	@Test
	void findById() {
		Specialty specialty = specialtyMapService.findById(aLong);
		assertEquals(aLong, specialty.getId());
	}
}