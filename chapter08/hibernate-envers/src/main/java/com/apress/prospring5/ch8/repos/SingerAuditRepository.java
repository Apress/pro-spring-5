package com.apress.prospring5.ch8.repos;

import com.apress.prospring5.ch8.entities.SingerAudit;
import org.springframework.data.repository.CrudRepository;

public interface SingerAuditRepository extends CrudRepository<SingerAudit, Long> {
}
