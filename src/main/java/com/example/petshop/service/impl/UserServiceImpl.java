package com.example.petshop.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.petshop.entity.ERole;
import com.example.petshop.entity.Role;
import com.example.petshop.entity.User;
import com.example.petshop.exception.NotFoundException;
import com.example.petshop.payload.request.LoginRequest;
import com.example.petshop.payload.request.RegisterRequest;
import com.example.petshop.repository.RoleRepository;
import com.example.petshop.repository.UserRepository;
import com.example.petshop.service.UserService;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void register(RegisterRequest request,String siteURL) throws UnsupportedEncodingException,MessagingException {
        // TODO Auto-generated method stub
        User user = new User();
          user.setUsername(request.getUsername());
          user.setEmail(request.getEmail());
          user.setPassword(encoder.encode(request.getPassword()));
          user.setEnabled(false);
          String randomCode = RandomString.make(64);
          user.setVerificationCode(randomCode);
          Set<String> strRoles = request.getRole();
          Set<Role> roles = new HashSet<>();
      
          if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
          } else {
            strRoles.forEach(role -> {
              switch (role) {
              case "admin":
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(adminRole);
      
                break;
              case "mod":
                Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(modRole);
      
                break;
              default:
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
              }
            });
          }
      
          user.setRoles(roles);
          userRepository.save(user);
          sendVerificationEmail(user, siteURL);
    }


    public void sendVerificationEmail(User user,String siteURL) throws UnsupportedEncodingException,MessagingException{
        String toAddress = user.getEmail();
        String fromAddress = "duongtuanphuong2@gmail.com";
        String senderName = "Pet Shop";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";
         
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
         
        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
         
        content = content.replace("[[URL]]", verifyURL);
         
        helper.setText(content, true);
         
        mailSender.send(message);
    }


    @Override
    public boolean verify(String verificationCode) {
      // TODO Auto-generated method stub
      User user = userRepository.findByVerificationCode(verificationCode);
      if(user == null || user.isEnabled()){
        return false;
      }else{
        user.setVerificationCode(null);
        user.setEnabled(true);
        userRepository.save(user);
        return true;
      }
    }


    @Override
    public User findUserByUserName(String username) {
      // TODO Auto-generated method stub
      User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Not Found User By Username: " +username));
      return user;
    }


    @Override
    public User getUser(long id) {
      // TODO Auto-generated method stub
      User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found user by id: " + id));
      return user;
    }



    
}
