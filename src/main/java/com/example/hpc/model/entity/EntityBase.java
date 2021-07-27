package com.example.hpc.model.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@MappedSuperclass
public class EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "create_date")
	private Instant createDate = new Date().toInstant();

	@Column(name = "update_date")
	private Instant updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Instant createDate) {
		this.createDate = createDate;
	}

	public Instant getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Instant updateDate) {
		this.updateDate = updateDate;
	}
}
