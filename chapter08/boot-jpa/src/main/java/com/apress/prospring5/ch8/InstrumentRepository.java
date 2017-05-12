package com.apress.prospring5.ch8;

import com.apress.prospring5.ch8.entities.Instrument;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by iuliana.cosmina on 5/12/17.
 */
public interface InstrumentRepository extends CrudRepository<Instrument, Long> {

}
