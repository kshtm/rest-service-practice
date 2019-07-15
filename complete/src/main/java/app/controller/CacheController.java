package app.controller;

import app.model.Greeting;
import app.service.GreetingService;
import com.google.common.cache.CacheBuilder;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
public class CacheController {

    @Autowired
    GreetingService greetingService;

    CacheLoader<Long, Greeting> cacheLoader = new CacheLoader<Long, Greeting>() {
        @Override
        public Greeting load(Long id) throws Exception {
            return greetingService.findById(id);
        }
    };

    LoadingCache<Long, Greeting> loadingCache = CacheBuilder.newBuilder()
            .refreshAfterWrite(1000, TimeUnit.HOURS)
            .build(cacheLoader);

    @GetMapping("/cache/greeting")
    public Greeting getGreetingFromCache() throws ExecutionException {
        Greeting greeting = loadingCache.get(1l);
        Greeting greeting1 = loadingCache.get(1L);
        return greeting;
    }

}
