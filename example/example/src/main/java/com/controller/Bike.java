package com.controller;

import com.example.model.Bike;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/bikes")
public class Bike {
    private final Map<Long, com.example.model.Bike> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @PostMapping
    public com.example.model.Bike createBike(@RequestBody com.example.model.Bike bike) {
        long id = idGenerator.incrementAndGet();
        bike.setId(id);
        store.put(id, bike);
        return bike;
    }

    @GetMapping
    public Collection<com.example.model.Bike> listBikes() {
        return store.values();
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.example.model.Bike> getBike(@PathVariable Long id) {
        com.example.model.Bike bike = store.get(id);
        if (bike == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.example.model.Bike> updateBike(@PathVariable Long id, @RequestBody com.example.model.Bike updated) {
        if (!store.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        updated.setId(id);
        store.put(id, updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBike(@PathVariable Long id) {
        store.remove(id);
        return ResponseEntity.noContent().build();
    }
}
