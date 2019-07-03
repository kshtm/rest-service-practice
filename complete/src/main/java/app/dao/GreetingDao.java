package app.dao;

import app.model.Greeting;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingDao extends JpaRepository<Greeting, Long> {

  Optional<Greeting> findByContent(String content);

}
