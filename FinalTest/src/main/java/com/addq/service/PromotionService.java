package com.addq.service;

import com.addq.model.Promotion;
import com.addq.repository.IPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PromotionService implements IPromotionService{
    @Autowired
    private IPromotionRepository promotionRepository;
    @Override
    public Page<Promotion> findAll(Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }

    @Override
    public Optional<Promotion> findById(Long id) {
        return promotionRepository.findById(id);
    }

    @Override
    public void save(Promotion promotion) {
        promotionRepository.save(promotion);
    }

    @Override
    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public Page<Promotion> findAllByTitleContaining(Optional<String> content, Pageable pageable) {
        return null;
    }
}
