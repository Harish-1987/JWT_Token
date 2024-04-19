package com.JwtToken.controller;

import com.JwtToken.entity.User;
import com.JwtToken.payload.LoginDto;
import com.JwtToken.payload.SignUpDto;
import com.JwtToken.repository.RoleRepository;
import com.JwtToken.repository.UserRepository;
import com.JwtToken.payload.LoginDto;
import com.JwtToken.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

     //Step1:
    //http://localhost:8080/api/auth/signin
     @Autowired
     private JwtTokenProvider tokenProvider;


    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }




    //http://localhost:8080/api/auth/signup
    //@PreAuthorize("permitAll()")
   // @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        Boolean Emailexists = userRepository.existsByEmail(signUpDto.getEmail());         //Before registration checking weather email is already present ?
        if(Emailexists){
            return new ResponseEntity<>("Entered email is already exists", HttpStatus.BAD_REQUEST);
        }

        Boolean UsernameExists = userRepository.existsByUsername(signUpDto.getUsername());
        if(UsernameExists){
            return new ResponseEntity<>("Entered UserName is already exists", HttpStatus.BAD_REQUEST);
        }


        User user = new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        User saved = userRepository.save(user);
        return new ResponseEntity<>("User is registered", HttpStatus.CREATED);
    }




//JWTAuthResponse is responsible to send back JWT token to postman/client
//Once you sign in Token should be return back signin feature above is responsible for that
}
