package com.jaguars.core.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "auth_token")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

    @Column(name = "user_email")
	private String userEmail;

    @Column(name = "token")
	private String token;

    @Column(name = "token_expiration_dt")
	private Date tokenExpirationDt;

    @Column(name = "refresh_token")
	private String refreshToken;

    @Column(name = "refresh_token_expiration_dt")
	private Date refreshTokenExpirationDt;

    @Column(name = "create_user")
	private String createUser;

    @Column(name = "create_dt")
	private Date createDt;

    @Column(name = "update_user")
	private String updateUser;

    @Column(name = "update_dt")
	private Date updateDt;

}
