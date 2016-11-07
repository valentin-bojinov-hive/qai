package com.qaiware.task.bojinov.storage.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Hibernate Entity representing the MessageRequestTable db model
 * @author valio
 *
 */
@Table(name="MessageRequestTable")
@Entity
public class MessageEntity implements Serializable {
	
	private static final long serialVersionUID = -8179567341531595116L;

	@Id	
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column
	private String type;
	
	@Column
	private String payload;
	
	@Column(name="created_at")
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	
	
}
