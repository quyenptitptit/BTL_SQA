package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.BaseMoney;

@Repository
public interface BaseMoneyRepository extends JpaRepository<BaseMoney, Long> {
}
