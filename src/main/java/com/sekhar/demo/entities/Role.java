package com.sekhar.demo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Role Entity
 * 
 * @author Sekhar
 *
 */
@Data
@EqualsAndHashCode(callSuper = false, exclude = { "accounts" })
@Entity
@Table(name = "roles")
public class Role extends Base implements Serializable {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 5847631802989582842L;

	@Column
	private String displayName;

	/**
	 * Role Name
	 */
	@Column(unique = true, name = "role_name")
	private String name;

	/**
	 * Role Description
	 */
	@Column(columnDefinition = "long")
	private String description;

	/**
	 * Accounts (One-to-Many)
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	@JsonIgnore
	private Set<Account> accounts = new HashSet<Account>();

	/**
	 * Default Constructor
	 */
	protected Role() {

	}

	/**
	 * @param name
	 * @param description
	 */
	public Role(String displayName, String name, String description) {
		this.displayName = displayName;
		this.name = name;
		this.description = description;
	}

}
