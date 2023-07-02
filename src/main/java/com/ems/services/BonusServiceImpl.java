package com.ems.services;

import com.ems.Payload.BonusDTO;
import com.ems.Repository.EmployeeRepository;
import com.ems.entities.Bonus;
import com.ems.Repository.BonusRepository;
import com.ems.entities.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BonusServiceImpl implements BonusService {

    private final BonusRepository bonusRepository;
    private final EmployeeRepository employeeRepository;

    public BonusServiceImpl(BonusRepository bonusRepository, EmployeeRepository employeeRepository) {
        this.bonusRepository = bonusRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<BonusDTO> getAllBonuses() {
        List<Bonus> bonuses = bonusRepository.findAll();
        return convertToDTOList(bonuses);
    }

    @Override
    public BonusDTO getBonusById(Long id) {
        Optional<Bonus> optionalBonus = bonusRepository.findById(id);
        return optionalBonus.map(this::convertToDTO).orElse(null);
    }

    @Override
    public BonusDTO createBonus(BonusDTO bonusDTO) {
        Bonus bonus = convertToEntity(bonusDTO);
        Bonus createdBonus = bonusRepository.save(bonus);
        return convertToDTO(createdBonus);
    }

    @Override
    public BonusDTO updateBonus(Long id, BonusDTO bonusDTO) {
        Optional<Bonus> optionalBonus = bonusRepository.findById(id);
        if (optionalBonus.isPresent()) {
            Bonus bonus = optionalBonus.get();
            bonus.setAmount(bonusDTO.getAmount());

            // Update the associated employee if necessary
            if (bonusDTO.getEmployeeId() != null) {
                Optional<Employee> optionalEmployee = employeeRepository.findById(bonusDTO.getEmployeeId());
                optionalEmployee.ifPresent(bonus::setEmployee);
            }

            Bonus updatedBonus = bonusRepository.save(bonus);
            return convertToDTO(updatedBonus);
        }
        return null;
    }

    @Override
    public void deleteBonus(Long id) {
        bonusRepository.deleteById(id);
    }

    private BonusDTO convertToDTO(Bonus bonus) {
        BonusDTO bonusDTO = new BonusDTO();
        bonusDTO.setBid(bonus.getBid());
        bonusDTO.setAmount(bonus.getAmount());
        bonusDTO.setEmployeeId(bonus.getEmployee() != null ? bonus.getEmployee().getEid() : null);
        return bonusDTO;
    }

    private List<BonusDTO> convertToDTOList(List<Bonus> bonuses) {
        return bonuses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private Bonus convertToEntity(BonusDTO bonusDTO) {
        Bonus bonus = new Bonus();
        bonus.setAmount(bonusDTO.getAmount());

        if (bonusDTO.getEmployeeId() != null) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(bonusDTO.getEmployeeId());
            optionalEmployee.ifPresent(bonus::setEmployee);
        }

        return bonus;
    }
}

