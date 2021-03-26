package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapServiceTest {

	private VisitMapService visitMapService;
	final Long aLong = 1L;

	@BeforeEach
	void setUp() {
		visitMapService = new VisitMapService();
		Visit visit = Visit.builder()
				              .id(aLong)
				              .pet(Pet.builder()
						                   .id(aLong)
						                   .owner(Owner.builder().id(aLong).build()).build()).build();
		visitMapService.save(visit);
	}

	@Test
	void findAll() {
		Set<Visit> visits = visitMapService.findAll();
		assertEquals(1, visits.size());
	}

	@Test
	void findById() {
		Visit visit = visitMapService.findById(aLong);
		assertEquals(aLong, visit.getId());
	}


	@Test
	void saveWithPetWithOwner() {
		Long id = 2L;
		Visit visit2 = Visit.builder()
				              .id(id)
				              .pet(Pet.builder()
						                   .id(id)
						                   .owner(Owner.builder().id(id).build()).build()).build();
		Visit savedVisit = visitMapService.save(visit2);
		assertEquals(visit2, savedVisit);
		assertEquals(id, savedVisit.getId());
	}

	@Test
	void saveWithPetWithoutOwner () {
		Long id = 2L;
		Visit visit2 = Visit.builder()
				               .id(id)
				               .pet(Pet.builder()
						                    .id(id)
						                    .build()).build();
		Exception expectedException = assertThrows(RuntimeException.class, () -> visitMapService.save(visit2));
		assertTrue(expectedException.getMessage().contains("invalid visit: no pet or no owner"));
	}

	@Test
	void saveWithoutPet() {
		Long id = 2L;
		Visit visit2 = Visit.builder().id(id).build();
		Exception expectedException = assertThrows(RuntimeException.class, () -> visitMapService.save(visit2));
		assertTrue(expectedException.getMessage().contains("invalid visit: no pet or no owner"));
	}

	@Test
	void delete() {
		visitMapService.delete(visitMapService.findById(aLong));
		assertEquals(0, visitMapService.findAll().size());
	}

	@Test
	void deleteById() {
		visitMapService.deleteById(aLong);
		assertEquals(0, visitMapService.findAll().size());
	}
}