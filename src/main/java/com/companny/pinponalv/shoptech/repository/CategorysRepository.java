package com.companny.pinponalv.shoptech.repository;

import com.companny.pinponalv.shoptech.model.Categorys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorysRepository extends JpaRepository<Categorys, Long> {
}
