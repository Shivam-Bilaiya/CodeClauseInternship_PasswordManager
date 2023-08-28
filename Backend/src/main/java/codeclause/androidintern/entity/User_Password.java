package codeclause.androidintern.entity;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "user_password")
public class User_Password {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passwordId;
	
	private String passwordLabel;
	
	private String password;
	
	private String passwordDate;

	public User_Password() {
		
	}
	
	
	public User_Password(String passwordLabel, String password,String passwordDate) {
		super();
		this.passwordLabel = passwordLabel;
		this.password = password;
		this.passwordDate = passwordDate;
	}

	public Long getPasswordId() {
		return passwordId;
	}

	public void setPasswordId(Long passwordId) {
		this.passwordId = passwordId;
	}

	public String getPasswordLabel() {
		return passwordLabel;
	}

	public void setPasswordLabel(String passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String getPasswordDate() {
		return passwordDate;
	}


	public void setPasswordDate(String passwordDate) {
		this.passwordDate = passwordDate;
	}


	@Override
	public String toString() {
		return "User_Password [passwordId=" + passwordId + ", passwordLabel=" + passwordLabel + ", password=" + password
				+ ", passwordDate=" + passwordDate + "]";
	}


	
	
	
	
	
	
	
	
	

}
