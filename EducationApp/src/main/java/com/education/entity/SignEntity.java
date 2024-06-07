package com.education.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//import java.security.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Getter
@Setter



@Entity
@Table(name="sign_details")
public class SignEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Long userId;
	@Column(name="mobile_number")
	private String mobileNumber;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="flg_of_usr")
	private String flagOfUser;
	@Column(name="active_flag")
	private String activeFlag;
	@Column(name="created_at")
	private LocalDateTime createdAt;
}
