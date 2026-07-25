package com.pas.xcontrolwms.wms.repository;

import com.pas.xcontrolwms.wms.model.ExplosiveRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExplosiveRegistryRepository extends JpaRepository<ExplosiveRegistry, Long> {
    boolean existsByXcontrolRegistryId(Long xcontrolRegistryd);

}
