package com.ems.services;

import com.ems.Payload.BonusDTO;

import java.util.List;

public interface BonusService {
    List<BonusDTO> getAllBonuses();
    BonusDTO getBonusById(Long id);
    BonusDTO createBonus(BonusDTO bonusDTO);
    BonusDTO updateBonus(Long id, BonusDTO bonusDTO);
    void deleteBonus(Long id);
}