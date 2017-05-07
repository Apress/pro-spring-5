package com.apress.prospring5.ch8.repos;

import com.apress.prospring5.ch8.entities.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by iuliana.cosmina on 5/7/17.
 */
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

}
