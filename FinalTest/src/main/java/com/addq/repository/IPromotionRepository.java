package com.addq.repository;

import com.addq.model.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromotionRepository extends PagingAndSortingRepository<Promotion, Long> {
    Page<Promotion> findAllByTitleContaining(String content, Pageable pageable);
}
