package com.ems.controller;

import com.ems.Payload.BonusDTO;
import com.ems.services.BonusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bonuses")
public class BonusRestController {

    private final BonusService bonusService;

    public BonusRestController(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    @GetMapping
    public ResponseEntity<List<BonusDTO>> getAllBonuses() {
        List<BonusDTO> bonuses = bonusService.getAllBonuses();
        return ResponseEntity.ok(bonuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonusDTO> getBonusById(@PathVariable("id") Long id) {
        BonusDTO bonus = bonusService.getBonusById(id);
        if (bonus != null) {
            return ResponseEntity.ok(bonus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BonusDTO> createBonus(@RequestBody BonusDTO bonusDTO) {
        BonusDTO createdBonus = bonusService.createBonus(bonusDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBonus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BonusDTO> updateBonus(@PathVariable("id") Long id, @RequestBody BonusDTO bonusDTO) {
        BonusDTO updatedBonus = bonusService.updateBonus(id, bonusDTO);
        if (updatedBonus != null) {
            return ResponseEntity.ok(updatedBonus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBonus(@PathVariable("id") Long id) {
        bonusService.deleteBonus(id);
        return ResponseEntity.noContent().build();
    }
}



