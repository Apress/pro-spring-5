package com.apress.prospring5.ch6.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Singer implements Serializable {

	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private List<Album> albums;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public boolean addAbum(Album album) {
		if (albums == null) {
			albums = new ArrayList<>();
			albums.add(album);
			return true;
		} else {
			if (albums.contains(album)) {
				return false;
			}
		}
		albums.add(album);
		return true;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String toString() {
		return "Singer - Id: " + id + ", First name: " + firstName
				+ ", Last name: " + lastName + ", Birthday: " + birthDate;
	}
}
