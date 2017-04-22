package com.apress.prospring5.ch7.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by iuliana.cosmina on 4/21/17.
 */
@Entity
@Table(name = "album")
public class Album extends AbstractEntity {

	@Column
	private String title;

	@Temporal(TemporalType.DATE)
	@Column(name = "RELEASE_DATE")

	private Date releaseDate;

	@ManyToOne
	@JoinColumn(name = "SINGER_ID")
	private Singer singer;

	public Singer getSinger() {
		return this.singer;
	}

	public String getTitle() {
		return this.title;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return String.format("Album - id: %d, Singer id: %d, Title: %s, Release Date: %s",
				id, singer.getId(), title, sdf.format(releaseDate));
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Album album = (Album) o;
		if (title != null ? !title.equals(album.title) : album.title != null)
			return false;
		return releaseDate != null ? releaseDate.equals(album.releaseDate) : album.releaseDate == null;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
		return result;
	}
}
