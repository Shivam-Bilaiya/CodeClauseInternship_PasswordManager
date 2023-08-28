package codeclause.androidintern.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codeclause.androidintern.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	User findByUserEmail(String userEmail);
}
