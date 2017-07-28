package com.apress.prospring5.ch16.entities;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "singer")
public class Singer implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Version
	@Column(name = "VERSION")
	private int version;

	@NotBlank(message = "{validation.firstname.NotBlank.message}")
	@Size(min = 2, max = 60, message = "{validation.firstname.Size.message}")
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotBlank(message = "{validation.lastname.NotBlank.message}")
	@Size(min = 1, max = 40, message = "{validation.lastname.Size.message}")
	@Column(name = "LAST_NAME")
	private String lastName;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "DESCRIPTION")
	private String description;

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Singer - Id: " + id + ", First name: " + firstName
				+ ", Last name: " + lastName + ", Birthday: " + birthDate
				+ ", Description: " + description;
	}
}
