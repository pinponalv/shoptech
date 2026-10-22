package com.companny.pinponalv.shoptech.repository;

import com.companny.pinponalv.shoptech.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
