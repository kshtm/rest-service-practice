package app.dao;

import app.model.Greeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GreetingDao extends PagingAndSortingRepository<Greeting, Long> {

}
