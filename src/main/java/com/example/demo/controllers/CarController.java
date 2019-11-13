package com.example.demo.controllers;

import com.example.demo.domain.Car;
import com.example.demo.respositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarRepository repo;  //It's a non blocking Mongo Repository

    // Mono/Fux are like a CompletableFuture object
    @GetMapping("/{id}")
    public Mono<Car> getCar(@PathVariable String id){
        return repo.findById(id);
    }


     // Mono = 0..1 objects
     // Mono object in the RequestBody permits that the IO operation of reading it
     // become a non blocking operation

    @PostMapping("/save")
    public void save(@RequestBody Mono<Car> car){
        car.subscribe(repo::save);
    }

    // Flux = 0..N objects
    // As previous method, Flux also permits IO operation become non blocking
    @PostMapping("/save/all")
    public void saveAll(@RequestBody Flux<Car> cars){
        repo.saveAll(cars);
    }

    // The important thing about this method is the use of "consumes" as a stream
    // It keeps the connection between client and server opened. So the client can
    // keep pushing new values any point in time, so the repository save the new values
    // in the database.
    // This is a live connection :)
    @PostMapping(value="/save", consumes = "application/stream+json")
    public void pushingCars(@RequestBody Flux<Car> cars){
        repo.saveAll(cars);
    }
}
