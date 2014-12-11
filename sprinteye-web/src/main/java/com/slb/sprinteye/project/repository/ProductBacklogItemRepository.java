package com.slb.sprinteye.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.slb.sprinteye.project.model.ProductBacklog;
import com.slb.sprinteye.project.model.ProductBacklogItem;

public interface ProductBacklogItemRepository extends JpaRepository<ProductBacklogItem, Long> {

		public Page<ProductBacklogItem> findAllByProductBacklog(ProductBacklog productBacklog,Pageable pageable );
			
}
