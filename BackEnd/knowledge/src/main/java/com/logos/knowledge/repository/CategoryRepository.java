package com.logos.knowledge.repository;

import com.logos.knowledge.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
    Category findByName(String category);
}
