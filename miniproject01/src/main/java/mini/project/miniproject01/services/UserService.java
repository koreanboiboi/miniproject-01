package mini.project.miniproject01.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini.project.miniproject01.dto.ResponseDto;
import mini.project.miniproject01.dto.user.SignInDto;
import mini.project.miniproject01.dto.user.SignInResponseDto;
import mini.project.miniproject01.dto.user.SignUpDto;
import mini.project.miniproject01.exceptions.AuthFail;
import mini.project.miniproject01.exceptions.CustomException;
import mini.project.miniproject01.models.AuthToken;
import mini.project.miniproject01.models.User;
import mini.project.miniproject01.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthService authSvc;

    @Transactional
    public ResponseDto signUp(SignUpDto signUpDto){

        if(Objects.nonNull(userRepo.findbyUserName(signUpDto.getUsername()))){
            throw new CustomException("This username already exists");
        }
        
        String encryptedPassword = signUpDto.getPassword();

        try {
            encryptedPassword = hashPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(signUpDto.getUsername(),encryptedPassword);
        userRepo.save(user);

        final AuthToken authToken = new AuthToken(user);
        authSvc.saveConfirmationToken(authToken);
        

        ResponseDto responseDto = new ResponseDto("success", "response message");

        return responseDto;

    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        
        MessageDigest md = MessageDigest.getInstance("MD4");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signinDto) {

        User user = userRepo.findbyUserName(signinDto.getUsername());

        if(Objects.isNull(user)){

            throw new AuthFail("Invalid user");
        }
        
        try {
            if (!user.getPassword().equals(hashPassword(signinDto.getPassword()))){
                throw new AuthFail("Wrong password");
            }
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AuthToken token = authSvc.getToken(user); 

        if(Objects.isNull(token)){

            throw new CustomException("No token");
        }
        return new SignInResponseDto("Success", token.getToken());
       
    }
    
}
