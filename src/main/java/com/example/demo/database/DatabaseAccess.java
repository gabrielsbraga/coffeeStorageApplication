package com.example.demo.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.demo.beans.Coffee;


@Configuration
public class DatabaseAccess {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	
		public void insertCoffee(String name, String origin, String coffeeType, String roast, long quantity) {
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			namedParameters.addValue("name", name);
			namedParameters.addValue("origin", origin);
			namedParameters.addValue("coffeeType", coffeeType);
			namedParameters.addValue("roast", roast);
			namedParameters.addValue("quantity", quantity);
			
			String query="INSERT INTO coffee(name, origin, coffee_type, roast, quantity) VALUES(:name,:origin,:coffeeType,:roast,:quantity)";
			
			int rowsAffected = jdbc.update(query, namedParameters);
			if (rowsAffected > 0)
				System.out.println("Inserted Coffee into database.");
			}
		
		public List<Coffee> getCoffees() {
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			String query = "SELECT * FROM coffee";
			
			return jdbc.query(query, namedParameters, 
					new BeanPropertyRowMapper<Coffee>(Coffee.class));

			}
		
		public List<Coffee> getCoffeeById(Long id) {
			
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				String query = "SELECT * FROM coffee WHERE id = :id";
				namedParameters.addValue("id", id);
				return jdbc.query(query, namedParameters, new
						BeanPropertyRowMapper<Coffee>(Coffee.class));
			}

		public List<Coffee> getCoffeeByName(String name) {
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			String query = "SELECT * FROM coffee WHERE name = :name";
			namedParameters.addValue("name", name);
			return jdbc.query(query, namedParameters, new
					BeanPropertyRowMapper<Coffee>(Coffee.class));
		}
		
		public void deleteCoffee(Long id) {
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			String query = "DELETE FROM coffee WHERE id = :id";
			
			namedParameters.addValue("id", id);
			int rowsAffected = jdbc.update(query, namedParameters);
			if (rowsAffected > 0)
			System.out.println("Deleted Coffee " + id + " from database");
			}
}
