package com.example.controller;

import com.example.model.Pen;
import com.example.dto.PenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pens")
public class Pen {
    private final Map<Long, com.example.model.Pen> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    // Convert Pen to PenDTO
    private PenDTO convertToDTO(com.example.model.Pen pen) {
        return new PenDTO(pen.getId(), pen.getBrand(), pen.getColor(), pen.getPrice());
    }

    // Convert PenDTO to Pen
    private com.example.model.Pen convertToEntity(PenDTO dto) {
        return new com.example.model.Pen(dto.getId(), dto.getBrand(), dto.getColor(), dto.getPrice());
    }

    @PostMapping
    public PenDTO createPen(@RequestBody PenDTO penDTO) {
        long id = idGenerator.incrementAndGet();
        penDTO.setId(id);
        com.example.model.Pen pen = convertToEntity(penDTO);
        store.put(id, pen);
        return convertToDTO(pen);
    }

    @GetMapping
    public Collection<PenDTO> listPens() {
        return store.values().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PenDTO> getPen(@PathVariable Long id) {
        com.example.model.Pen pen = store.get(id);
        if (pen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDTO(pen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PenDTO> updatePen(@PathVariable Long id, @RequestBody PenDTO penDTO) {
        if (!store.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        penDTO.setId(id);
        com.example.model.Pen pen = convertToEntity(penDTO);
        store.put(id, pen);
        return ResponseEntity.ok(convertToDTO(pen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePen(@PathVariable Long id) {
        store.remove(id);
        return ResponseEntity.noContent().build();
    }
}
