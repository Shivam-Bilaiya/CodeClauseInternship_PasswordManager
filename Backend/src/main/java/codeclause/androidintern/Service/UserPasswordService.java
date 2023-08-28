package codeclause.androidintern.Service;

import org.springframework.stereotype.Service;

import codeclause.androidintern.DTO.User_PasswordDTO;
import codeclause.androidintern.entity.User_Password;

@Service
public interface UserPasswordService {
	
	
	public User_PasswordDTO updateUser_Password(Long passwordId,User_Password user_Password);
	
	public Boolean deleteUserPassword(Long passwordId);

}
