package com.apress.prospring5.ch7.base.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 4/21/17.
 */
@Entity
@Table(name = "instrument")
public class Instrument implements Serializable {
	private String instrumentId;
	private Set<Singer> singers = new HashSet<>();

	@Id
	@Column(name = "INSTRUMENT_ID")
	public String getInstrumentId() {
		return this.instrumentId;
	}

	@ManyToMany
	@JoinTable(name = "singer_instrument",
			joinColumns = @JoinColumn(name = "INSTRUMENT_ID"),
			inverseJoinColumns = @JoinColumn(name = "SINGER_ID"))
	public Set<Singer> getSingers() {
		return this.singers;
	}

	public void setSingers(Set<Singer> singers) {
		this.singers = singers;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	@Override
	public String toString() {
		return "Instrument :" + getInstrumentId();
	}
}