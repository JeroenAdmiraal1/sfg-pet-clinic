package guru.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity{

	@Builder
	public Specialty(Long id, String description){
		super(id);
		this.description = description;
	}

	@Column(name = "description")
	private String description;

}
