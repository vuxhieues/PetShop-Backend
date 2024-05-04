package com.example.petshop.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.petshop.payload.request.LoginRequest;
import com.example.petshop.payload.request.RegisterRequest;
import com.example.petshop.payload.response.MessageResponse;
import com.example.petshop.payload.response.UserInfoResponse;
import com.example.petshop.repository.RoleRepository;
import com.example.petshop.repository.UserRepository;
import com.example.petshop.security.jwt.JwtUtils;
import com.example.petshop.security.service.UserDetailsImpl;
import com.example.petshop.service.UserService;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
        // return ResponseEntity.ok(jwtCookie);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest,HttpServletRequest request) throws UnsupportedEncodingException,MessagingException{
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
          }
      
          if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
          }
      
          userService.register(registerRequest,getSiteUrl(request));
      
          return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
      ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
          .body(new MessageResponse("You've been logout!"));
    }

    private String getSiteUrl(HttpServletRequest request){
      String siteURL = request.getRequestURL().toString();
      return  siteURL.replace(request.getServletPath(),"");
    }
}
