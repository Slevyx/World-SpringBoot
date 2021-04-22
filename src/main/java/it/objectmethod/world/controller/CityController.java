package it.objectmethod.world.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.world.dao.ICityDao;
import it.objectmethod.world.models.City;

@Controller
public class CityController {

	@Autowired
	private ICityDao cityDao;
	
	@GetMapping("/city")
	public String landToCityPage() {
		return "City";
	}
	
	@PostMapping("/city")
	public String getCityByName(@RequestParam("cityName") String cityName, ModelMap map) throws SQLException {
		City city = null;
		if(cityName == null || cityName.isBlank()) {
			map.addAttribute("error", "City field cannot be empty.");
		}
		else {
			cityName = cityName.toUpperCase();
			city = cityDao.getCityByName(cityName);
			if(city == null) {
				map.addAttribute("error", "Nothing was found.");
			}
		}
		map.addAttribute("city", city);
		return "City";
	}
	
	@GetMapping("/{countryCode}/cities")
	public String getCitiesByCountry(@PathVariable("countryCode") String countryCode, ModelMap map) throws SQLException {
		List<City> cities = cityDao.getCitiesByCountry(countryCode);
		map.addAttribute("cities", cities);
 		return "Cities";
	}
}
