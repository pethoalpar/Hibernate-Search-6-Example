package com.pethoalpar.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pethoalpar.entity.Animal;
import com.pethoalpar.entity.AnimalProperty;
import com.pethoalpar.entity.Continent;
import com.pethoalpar.repository.ContinentRepository;
import com.pethoalpar.service.api.IContinentService;

/**
 * @author alpar.petho
 *
 */
@RestController
@RequestMapping("/continent")
public class ContinentController {
	
	@Autowired
	private ContinentRepository continentRepository;
	
	@Autowired
	private IContinentService continentService;
	
	@GetMapping(value = "/findAll")
	@ResponseBody
	public ResponseEntity<List<Continent>> findAll() {
		List<Continent> retList = continentRepository.findAll();
		return new ResponseEntity<>(retList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/{searchKey}")
	@ResponseBody
	public ResponseEntity<List<Continent>> search(@PathVariable String searchKey) {
		List<Continent> retList = continentService.elasticSearch(searchKey);
		return new ResponseEntity<>(retList, HttpStatus.OK);
	}
	
	@PostMapping(value = "/insert")
	@ResponseBody
	public ResponseEntity<List<Continent>> insert(@RequestBody List<Continent> continents) {
		if(continents != null && continents.size()>0) {
			for(Continent continent : continents) {
				if(continent.getAnimals() != null) {
					for(Animal animal : continent.getAnimals()) {
						animal.setContinent(continent);
						if(animal.getProperties() != null) {
							for(AnimalProperty animalProperty : animal.getProperties()) {
								animalProperty.setAnimal(animal);
							}
						}
					}
				}
			}
		}
		List<Continent> retList = continentRepository.saveAll(continents);
		return new ResponseEntity<>(retList, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	@ResponseBody
	public ResponseEntity<String> delete(@PathVariable Long id) {
		continentRepository.deleteById(id);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}

}
