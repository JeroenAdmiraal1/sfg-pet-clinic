package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

	@Override
	public Set<Visit> findAll() {
		return super.findAll();
	}

	@Override
	public Visit findById(Long aLong) {
		return super.findById(aLong);
	}

	@Override
	public Visit save(Visit visit) {

		if (visit.getPet() == null ||
				    visit.getPet().getId() == null ||
				    visit.getPet().getOwner() == null ||
				    visit.getPet().getOwner().getId() == null){
			throw new RuntimeException("invalid visit: no pet or no owner");
		} else return super.save(visit);
	}

	@Override
	public void delete(Visit object) {
		super.delete(object);
	}

	@Override
	public void deleteById(Long aLong) {
		super.deleteById(aLong);
	}
}
