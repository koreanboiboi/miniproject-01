package mini.project.miniproject01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mini.project.miniproject01.dto.ResponseDto;
import mini.project.miniproject01.dto.user.SignUpDto;
import mini.project.miniproject01.services.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userSvc;

    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody SignUpDto signUpDto){

        return userSvc.signUp(signUpDto);

    }
    
}
