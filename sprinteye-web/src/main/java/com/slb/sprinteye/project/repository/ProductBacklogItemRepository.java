package com.slb.sprinteye.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slb.sprinteye.project.model.ProductBacklogItem;

public interface ProductBacklogItemRepository extends JpaRepository<ProductBacklogItem, Long> {

}
