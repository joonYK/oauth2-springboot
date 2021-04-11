package com.jy.study.oauth2.legacyUsersService.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 5313493413859894403L;
 
    public UserEntity() {}
    
    public UserEntity(long id, String userId, String firstName, String lastName, String email, String encryptedPassword,
                      String emailVerificationToken, Boolean emailVerificationStatus) {
		this.id = id;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.encryptedPassword = encryptedPassword;
		this.emailVerificationToken = emailVerificationToken;
		this.emailVerificationStatus = emailVerificationStatus;
	}

	@Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 120)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    private String emailVerificationToken;

    @Column(nullable = false)
    private Boolean emailVerificationStatus = false;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
