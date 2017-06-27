package com.sekhar.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.tomcat.jni.Global;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Base entity which should be extends for all entity
 * 
 * @author Sekhar
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class Base implements Serializable {

	private static final long serialVersionUID = 8766891123160277608L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "pk_id")
	private String id;

	@Column
	private Boolean deleted = false;

	@Column
	private Boolean active = true;

	@Version
	@Column(length = Global.VARCHAR16)
	private Integer numberOfChange = 0;

	/**
	 * Indicate whether this record is restricted to delete
	 */
	@Column
	private Boolean isRestricted = false;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column
	private String createdBy;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Column
	private String lastModifiedBy;

	@PrePersist
	void onCreate() {
		this.setCreatedDate(new Date());
		this.setLastModifiedDate(new Date());
		this.setCreatedBy(SecurityContextUtil.getCurrentUsername());
		this.setLastModifiedBy(SecurityContextUtil.getCurrentUsername());
	}

	@PreUpdate
	void onUpdate() {
		this.setLastModifiedDate(new Date());
		this.setLastModifiedBy(SecurityContextUtil.getCurrentUsername());
	}

}
