package codeclause.androidintern.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codeclause.androidintern.DTO.User_PasswordDTO;
import codeclause.androidintern.Repository.UserPasswordRepo;
import codeclause.androidintern.entity.User_Password;

@Service
public class UserPasswordServiceImpl implements UserPasswordService{

	@Autowired
	private UserPasswordRepo userPasswordRepo;
	
	
	@Override
	public User_PasswordDTO updateUser_Password(Long passwordId, User_Password user_Password) {
		User_Password user_Password2 = userPasswordRepo.findById(passwordId).get();
		user_Password2.setPasswordLabel(user_Password.getPasswordLabel());
		user_Password2.setPassword(user_Password.getPassword());
		user_Password = userPasswordRepo.save(user_Password2);
		User_PasswordDTO user_PasswordDTO = new User_PasswordDTO(user_Password.getPasswordId(),user_Password.getPasswordLabel(),user_Password.getPassword(),user_Password.getPasswordDate());
		return user_PasswordDTO;
	}

	@Override
	public Boolean deleteUserPassword(Long passwordId) {
		userPasswordRepo.deleteById(passwordId);	
		return true;
	}

}
