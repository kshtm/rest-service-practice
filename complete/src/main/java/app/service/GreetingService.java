package app.service;

import app.model.Greeting;

import java.util.List;

public interface GreetingService {

  public Greeting save(String content);

  public Greeting findById(long id);

  public List<Greeting> findAll();
}
