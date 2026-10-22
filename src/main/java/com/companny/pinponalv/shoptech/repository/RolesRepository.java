package com.companny.pinponalv.shoptech.repository;

import com.companny.pinponalv.shoptech.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
}
