package com.sekhar.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Account Entity
 * 
 * @author Sekhar
 *
 */
@Data
@EqualsAndHashCode(callSuper = false, exclude = { "role" })
@Entity
@Table(name = "accounts")
public class Account extends Base {

	private static final long serialVersionUID = 5847631802989582842L;

	/**
	 * Username
	 */
	@Column
	private String username;

	/**
	 * Password
	 */
	@Column
	private String password;
	

	/**
	 * Email
	 */
	@Column
	private String email;

	/**
	 * Fullname
	 */
	@Column
	private String fullname;

	/**
	 * NRIC
	 */
	@Column
	private String nric;

	/**
	 * Date of birth
	 */
	@Column
	// @JsonSerialize(using = JsonDateSerializer.class)
	private Date dob;

	/**
	 * Gender
	 */
	@Column
	private String gender;
	
	/**
	 * 
	 */
	@Column
	private String wechatId;

	/**
	 * Role
	 */
//	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED) //the source entity is audited, but the target is not.
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	@NotNull
	private Role role;

	/**
	 * Default Constructor
	 */
	protected Account() {

	}

	/**
	 * Constructor with username, password, email and Role
	 * 
	 * @param email
	 * @param password
	 * @param role
	 */
	public Account(String username, String password, String email, String fullname, Role role) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.role = role;
	}
	
	public Account(String username, String password, String email, String fullname, Role role, boolean restricted) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.role = role;
		this.setIsRestricted(restricted);
	}
	
	@Transient
	private String roleId;

	/**
	 * Transient value for Role Id
	 * 
	 * @return
	 */
	public String getRoleId() {
		return role != null ? role.getId() : null;
	}

	@Transient
	public String getRoleValue() {
		return role != null ? role.getName() : null;
	}
	
	
	@Transient
	public String oldPassword;
	
	@Transient
	public String confirmedPassword;
	
	@Transient
	public String newPassword;
	

}
