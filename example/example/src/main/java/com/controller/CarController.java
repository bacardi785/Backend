package com.controller;

import com.example.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final Map<Long, Car> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        long id = idGenerator.incrementAndGet();
        car.setId(id);
        store.put(id, car);
        return car;
    }

    @GetMapping
    public Collection<Car> listCars() {
        return store.values();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        Car car = store.get(id);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updated) {
        if (!store.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        updated.setId(id);
        store.put(id, updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        store.remove(id);
        return ResponseEntity.noContent().build();
    }
}