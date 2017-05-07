package com.apress.prospring5.ch8.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

/**
 * Created by iuliana.cosmina on 5/7/17.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<U> implements Serializable {
	@CreatedDate
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	@CreatedBy
	@Column(name = "CREATED_BY")
	protected String createdBy;

	@LastModifiedBy
	@Column(name = "LAST_MODIFIED_BY")
	protected String lastModifiedBy;

	@LastModifiedDate
	@Column(name = "LAST_MODIFIED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModifiedDate;

	public Optional<String> getCreatedBy() {
		return Optional.of(createdBy);
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Optional<Date> getCreatedDate() {
		return Optional.of(createdDate);
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Optional<String> getLastModifiedBy() {
		return Optional.of(lastModifiedBy);
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Optional<Date> getLastModifiedDate() {
		return Optional.of(lastModifiedDate);
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
