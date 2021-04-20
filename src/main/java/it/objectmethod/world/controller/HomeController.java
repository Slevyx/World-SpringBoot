package it.objectmethod.world.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.world.dao.ICityDao;
import it.objectmethod.world.dao.ICountryDao;
import it.objectmethod.world.models.City;
import it.objectmethod.world.models.Country;

@Controller
public class HomeController {

	@Autowired
	private ICityDao cityDao;
	@Autowired
	private ICountryDao countryDao;
	
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, ModelMap map, HttpServletRequest request) {
		String forwardTo = "City";
		if(username == null || username.isBlank()) {
			forwardTo = "Login";
			map.addAttribute("error", "Username cannot be empty.");
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("loggedUser", username);
		}
		return forwardTo;
	}
	
	@GetMapping("/login")
	public String landToLoginPage() {
		return "Login";
	}
	
	@GetMapping("/city")
	public String landToCityPage() {
		return "City";
	}
	
	@PostMapping("/city")
	public String getCityByName(@RequestParam("cityName") String cityName, ModelMap map) {
		City city = null;
		if(cityName == null || cityName.isBlank()) {
			map.addAttribute("error", "City field cannot be empty.");
		}
		else {
			try {
				cityName = cityName.toUpperCase();
				city = cityDao.getCityByName(cityName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(city == null) {
				map.addAttribute("error", "Nothing was found.");
			}
		}
		map.addAttribute("city", city);
		return "City";
	}
	
	@GetMapping("/countries")
	public String landToCountriesPage() {
		return "Countries";
	}
	
	@PostMapping("/countries")
	public String getCountriesByCountryNameContinentName(
			@RequestParam("countryName") String countryName, @RequestParam("continentName") String continentName, ModelMap map) {
		List<Country> countryList = new ArrayList<>();
		if(countryName == null || continentName == null) {
			map.addAttribute("error", "Null fields.");
		}
		else {
			try {
				countryName = countryName.toUpperCase();
				continentName = continentName.toUpperCase();
				countryList = countryDao.getCountriesByCountryNameContinentName(countryName, continentName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(countryList.isEmpty()) {
				map.addAttribute("error", "Nothing was found.");
			}
		}
		map.addAttribute("countryList", countryList);
		return "Countries";
	}
	
	@RequestMapping("/continents")
	public String getContinents(ModelMap map) throws SQLException {
		List<String> continentsList = new ArrayList<>();
		continentsList = countryDao.getContinents();
		map.addAttribute("continentsList", continentsList);
		return "Continents";
	}
	
	@RequestMapping("/{continent-name}/countries")
	public String getCountriesByContinent(@PathVariable("continent-name"), ModelMap map) {
		return "";
	}
}
