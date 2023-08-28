package codeclause.androidintern.DTO;

import java.time.LocalDate;
import java.util.Date;

public class User_PasswordDTO {
	
	
	private Long passwordId;
	
	private String passwordLabel;
	
	private String password;
	
	private String passwordDate;

	public User_PasswordDTO() {
		
	}
	
	
	public User_PasswordDTO(Long passwordId,String passwordLabel, String password,String passwordDate) {
		super();
		this.passwordId = passwordId;
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
		return "User_PasswordDTO [passwordId=" + passwordId + ", passwordLabel=" + passwordLabel + ", password="
				+ password + ", passwordDate=" + passwordDate + "]";
	}


	
	
	

}
