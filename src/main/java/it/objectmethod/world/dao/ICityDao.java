package it.objectmethod.world.dao;

import java.sql.SQLException;

import it.objectmethod.world.models.City;

public interface ICityDao {

	public City getCityByName(String cityName) throws SQLException;
}
