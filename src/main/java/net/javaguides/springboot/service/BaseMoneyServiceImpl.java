package net.javaguides.springboot.service;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.BaseMoney;
import net.javaguides.springboot.repository.BaseMoneyRepository;

@Service
public class BaseMoneyServiceImpl implements BaseMoneyService {
    private BaseMoneyRepository baseMoneyRepository;

    public BaseMoneyServiceImpl(BaseMoneyRepository baseMoneyRepository) {
        super();
        this.baseMoneyRepository = baseMoneyRepository;
    }

    @Override
    public BaseMoney getBaseMoney() {
        BaseMoney baseMoney = baseMoneyRepository.findById(1L).get();
        return baseMoney;
    }
}
