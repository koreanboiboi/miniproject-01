package mini.project.miniproject01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mini.project.miniproject01.models.AuthToken;

public interface TokenRepository extends JpaRepository<AuthToken,Integer> {
    
}
