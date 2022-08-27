package mini.project.miniproject01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini.project.miniproject01.models.AuthToken;
import mini.project.miniproject01.models.User;
import mini.project.miniproject01.repositories.TokenRepository;

@Service
public class AuthService {


    @Autowired
    private TokenRepository tokenRepo;


    public void saveConfirmationToken(AuthToken authToken) {

        tokenRepo.save(authToken);

    }

    public AuthToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }
    
}
