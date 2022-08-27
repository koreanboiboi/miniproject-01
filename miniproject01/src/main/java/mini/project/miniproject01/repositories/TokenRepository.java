package mini.project.miniproject01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mini.project.miniproject01.models.AuthToken;
import mini.project.miniproject01.models.User;

@Repository
public interface TokenRepository extends JpaRepository<AuthToken,Integer> {

   AuthToken findByUser(User user); 
    
}
