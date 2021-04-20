package it.objectmethod.world.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.world.dao.ICityDao;
import it.objectmethod.world.models.City;

@Component
public class CityDaoImpl implements ICityDao{
	
	@Autowired
	DataSource dataSource;

	@Override
	public City getCityByName(String cityName) throws SQLException {
		Connection connection = dataSource.getConnection();
		City city = null;
		String sqlQuery = "SELECT * FROM city WHERE UPPER(Name) = ?";
		PreparedStatement statement = connection.prepareStatement(sqlQuery);
		statement.setString(1, cityName);
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			city = new City();
			city.setId(result.getInt("ID"));
			city.setName(result.getString("Name"));
			city.setCountryCode(result.getString("CountryCode"));
			city.setDistrict(result.getString("District"));
			city.setPopulation(result.getInt("Population"));
		}
		result.close();
		statement.close();
		connection.close();
		return city;
	}
}
