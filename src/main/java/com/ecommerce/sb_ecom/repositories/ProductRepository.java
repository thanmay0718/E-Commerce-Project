package com.ecommerce.sb_ecom.repositories;

import com.ecommerce.sb_ecom.Model.Category;
import com.ecommerce.sb_ecom.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryOrderByPriceAsc(Pageable pageDetails, Category category);

    Page<Product> findByProductNameLikeIgnoreCase(String keyword, Pageable pageDetails);
}
