package it.objectmethod.world.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.world.dao.ICountryDao;
import it.objectmethod.world.models.Country;

@Controller
public class CountryController {

	@Autowired
	private ICountryDao countryDao;
	
	@GetMapping("/countries")
	public String landToCountriesPage() {
		return "CountriesList";
	}
	
	@PostMapping("/countries")
	public String getCountriesByCountryNameContinentName(
			@RequestParam("countryName") String countryName, @RequestParam("continentName") String continentName, ModelMap map) {
		List<Country> countriesList = new ArrayList<>();
		if(countryName == null || continentName == null) {
			map.addAttribute("error", "Null fields.");
		}
		else {
			countryName = countryName.toUpperCase();
			continentName = continentName.toUpperCase();
			countriesList = countryDao.getCountriesByCountryNameContinentName(countryName, continentName);
			if(countriesList.isEmpty()) {
				map.addAttribute("error", "Nothing was found.");
			}
		}
		map.addAttribute("countriesList", countriesList);
		return "CountriesList";
	}
	
	@GetMapping("/continents")
	public String getContinents(ModelMap map) {
		List<String> continentsList = new ArrayList<>();
		continentsList = countryDao.getContinents();
		map.addAttribute("continentsList", continentsList);
		return "Continents";
	}
	
	@GetMapping("/{continentName}/countries")
	public String getCountriesByContinent(@PathVariable("continentName") String continentName, ModelMap map) {
		List<Country> countries = new ArrayList<>();
		countries = countryDao.getCountriesbyContinentName(continentName);
		map.addAttribute("countries", countries);
		return "Countries";
	}
}
