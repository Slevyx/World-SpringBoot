package it.objectmethod.world.dao;

import java.sql.SQLException;
import java.util.List;

import it.objectmethod.world.models.City;

public interface ICityDao {

	public City getCityByName(String cityName) throws SQLException;

	public List<City> getCitiesByCountry(String countryCode) throws SQLException;
}
