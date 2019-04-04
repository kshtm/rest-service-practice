package app.dao;

import app.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingDao extends JpaRepository<Greeting, Long> {

}
