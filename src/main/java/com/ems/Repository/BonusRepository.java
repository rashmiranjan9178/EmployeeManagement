package com.ems.Repository;

import com.ems.entities.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonusRepository extends JpaRepository<Bonus,Long> {
}
