package com.apress.prospring5.ch7.entities;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by iuliana.cosmina on 4/22/17.
 */
@Entity
@Table(name = "singer")
@NamedQueries({
		@NamedQuery(name=Singer.FIND_SINGER_BY_ID,
				query="select distinct s from Singer s " +
						"left join fetch s.albums a " +
						"left join fetch s.instruments i " +
						"where s.id = :id"),
		@NamedQuery(name=Singer.FIND_ALL_WITH_ALBUM,
				query="select distinct s from Singer s " +
						"left join fetch s.albums a " +
						"left join fetch s.instruments i")
})
public class Singer extends AbstractEntity {

	public static final String FIND_SINGER_BY_ID = "Singer.findById";
	public static final String FIND_ALL_WITH_ALBUM = "Singer.findAllWithAlbum";

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@OneToMany(mappedBy = "singer", cascade=CascadeType.ALL,
			orphanRemoval=true)
	private Set<Album> albums = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "singer_instrument",
			joinColumns = @JoinColumn(name = "SINGER_ID"),
			inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
	private Set<Instrument> instruments = new HashSet<>();


	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}


	public Set<Album> getAlbums() {
		return albums;
	}

	public Set<Instrument> getInstruments() {
		return instruments;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean addAbum(Album album) {
		album.setSinger(this);
		return albums.add(album);
	}

	public void removeAlbum(Album album) {
		albums.remove(album);
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
	}

	public boolean addInstrument(Instrument instrument) {
		return instruments.add(instrument);
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return String.format("Singer - id: %d, First name: %s, Last name: %s, Birthday: %s",
				id, firstName, lastName, sdf.format(birthDate));
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Singer singer = (Singer) o;
		if (firstName != null ? !firstName.equals(singer.firstName) : singer.firstName != null)
			return false;
		if (lastName != null ? !lastName.equals(singer.lastName) : singer.lastName != null)
			return false;
		return birthDate != null ? birthDate.equals(singer.birthDate) : singer.birthDate == null;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
		return result;
	}
}
