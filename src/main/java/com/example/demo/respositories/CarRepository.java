package com.example.demo.respositories;

import com.example.demo.domain.Car;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends ReactiveCrudRepository<Car, String> {

}
