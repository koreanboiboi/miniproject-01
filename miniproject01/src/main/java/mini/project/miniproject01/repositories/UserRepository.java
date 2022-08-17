package mini.project.miniproject01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mini.project.miniproject01.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findbyUserName(String username);
    
}
