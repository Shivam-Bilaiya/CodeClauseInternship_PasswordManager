package codeclause.androidintern.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codeclause.androidintern.entity.User_Password;

@Repository
public interface UserPasswordRepo extends JpaRepository<User_Password, Long>{

}
