package com.addq.service;

import com.addq.model.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface IPromotionService {
    Page<Promotion> findAll(Pageable pageable);
    Optional<Promotion> findById(Long id);
    void save(Promotion blog);
    void delete(Long id);
    Page<Promotion> findAllByTitleContaining(Optional<String> content, Pageable pageable);
}
