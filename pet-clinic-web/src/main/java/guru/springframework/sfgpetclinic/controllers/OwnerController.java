package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
	private static final String FIND_OWNERS = "owners/findOwners";
	private static final String VIEWS_OWNERS_LIST = "owners/ownersList";

	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder){
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping({"/find"})
	public String findOwners(Model model){

		model.addAttribute("owner", Owner.builder().build());

		return FIND_OWNERS;
	}

	@GetMapping
	public String processFindForm(Owner owner, BindingResult result, Model model){
		if(owner.getLastName() == null){
			owner.setLastName("");
		}

		List<Owner> owners = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

		if(owners.isEmpty()){
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		} else if(owners.size() == 1){
			owner = owners.get(0);
			return "redirect:/owners/" + owner.getId();
		} else {
			model.addAttribute("selections", owners);
			return VIEWS_OWNERS_LIST;
		}
	}

	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
		ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
		modelAndView.addObject(ownerService.findById(ownerId));
		return modelAndView;
	}

	@GetMapping("/new")
	public String initCreationForm(Model model){
		Owner owner = Owner.builder().build();
		model.addAttribute("owner", owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result){
		if(result.hasErrors()){
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}

	@GetMapping("/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable String ownerId, Model model){
		Owner owner = ownerService.findById(Long.valueOf(ownerId));
		model.addAttribute("owner", owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable String ownerId){
		if(result.hasErrors()){
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			owner.setId(Long.valueOf(ownerId));
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}

}
