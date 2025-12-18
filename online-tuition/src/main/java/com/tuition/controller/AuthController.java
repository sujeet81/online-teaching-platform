package com.tuition.controller;

import com.tuition.entity.Role;
import com.tuition.entity.User;
import com.tuition.repository.RoleRepository;
import com.tuition.repository.UserRepository;
import com.tuition.security.JwtUtil;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        Role role = roleRepository.findByName("STUDENT")
                .orElseThrow(() -> new RuntimeException("Role Not Found"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRole(role);
      userRepository.save(user);

      return "User Registered Successfully ";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        User dbUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));

        if(!passwordEncoder.matches(user.getPassword(),dbUser.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }
        return jwtUtil.generateToken(dbUser.getEmail(), dbUser.getRole().getName());
    }
}
