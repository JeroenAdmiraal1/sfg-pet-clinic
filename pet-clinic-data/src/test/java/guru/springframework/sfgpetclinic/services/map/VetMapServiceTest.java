package guru.springframework.sfgpetclinic.services.map;


import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VetMapServiceTest {

	private VetMapService vetMapService;
	final Long vetId = 1L;

	@BeforeEach
	void setUp() {
		vetMapService = new VetMapService(new SpecialtyMapService());
		vetMapService.save(Vet.builder().id(1L).build());
	}

	@Test
	void findAll() {
		Set<Vet> vets = vetMapService.findAll();
		assertEquals(1, vets.size());
	}

	@Test
	void deleteById() {
		vetMapService.deleteById(vetId);
		assertEquals(0, vetMapService.findAll().size());
	}

	@Test
	void delete() {
		vetMapService.delete(vetMapService.findById(vetId));
		assertEquals(0, vetMapService.findAll().size());
	}

	@Test
	void saveWithoutSpecialty() {
		Long id = 2L;
		Vet vet2 = Vet.builder().id(id).build();
		Vet savedVet = vetMapService.save(vet2);
		assertEquals(vet2, savedVet);
		assertEquals(id, savedVet.getId());
	}

	@Test
	void saveWithSpecialty() {
		Long id = 2L;
		Set<Specialty> specialties = new HashSet<>();
		specialties.add(Specialty.builder().build());
		Vet vet2 = Vet.builder().id(id).specialties(specialties).build();
		Vet savedVet = vetMapService.save(vet2);
		assertEquals(vet2, savedVet);
		assertEquals(id, savedVet.getId());
	}

	@Test
	void findById() {
		Vet vet = vetMapService.findById(vetId);
		assertEquals(vetId, vet.getId());
	}
}