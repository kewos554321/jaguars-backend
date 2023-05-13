package com.jaguars.core.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "auth_user")
// @Table(name = "auth_user", schema = "Common")
public class UserEntity {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;

    @Column(name = "account")
	private String account;

    @Column(name = "email", unique = true, nullable = false)
	private String email;

    @Column(name = "password", nullable = false)
	private String password;

    @Column(name = "name")
	private String name;

    @Column(name = "status")
	private String status;

    @Column(name = "error_cnt")
	private Integer errorCnt;

    @Column(name = "last_fail_login_dt")
	private Date lastFailLoginDt;

    @Column(name = "last_login_dt")
	private Date lastLoginDt;

    @Column(name = "last_logout_dt")
	private Date lastLogoutDt;

    @Column(name = "type")
	private String type;

    @Column(name = "create_user")
	private String createUser;

    @Column(name = "create_dt")
	private Date createDt;

    @Column(name = "update_user")
	private String updateUser;

    @Column(name = "update_dt")
	private Date updateDt;

    @Column(name = "is_activated")
	private Integer isActivated;
}
