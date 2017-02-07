package rs.ac.uns.ftn.informatika.jpa.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sms.beans.City;
import com.sms.dao.CityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryIntegrationTests {

	@Autowired
	CityRepository repository;

	@Test
	public void findsFirstPageOfCities() {

		Page<City> cities = this.repository.findAll(new PageRequest(0, 10));
		assertThat(cities.getTotalElements()).isGreaterThan(20L);
	}
}
