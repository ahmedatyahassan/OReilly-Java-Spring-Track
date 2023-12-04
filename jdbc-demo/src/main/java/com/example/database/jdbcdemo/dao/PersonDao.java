package com.example.database.jdbcdemo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.database.jdbcdemo.entity.Person;

@Repository
public class PersonDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getDate("birth_date"));
			return person;
		}

	}

	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person", new PersonRowMapper());
	}

	public Person findById(int id) {
		return jdbcTemplate.queryForObject("select * from person where id=?",
				new BeanPropertyRowMapper<Person>(Person.class), new Object[] { id });
	}

	public List<Person> findByLocation(String location, String name) {

		return jdbcTemplate.query("select * from person where location=? and name=?",
				new BeanPropertyRowMapper<Person>(Person.class), new Object[] { location, name });

	}

	public int deleteById(int id) {
		return jdbcTemplate.update("delete from person where id=?", new Object[] { id });
	}

	public int insert(Person person) {
		return jdbcTemplate.update("insert into person (id, name, location, birth_date) values(?, ?, ?, ?)",
				new Object[] { person.getId(), person.getName(), person.getLocation(), person.getBirthDate() });
	}

	public int update(Person person) {
		return jdbcTemplate.update("update person set name=?, location=?, birth_date=? where id=?",
				new Object[] { person.getName(), person.getLocation(), person.getBirthDate(), person.getId() });
	}

}
