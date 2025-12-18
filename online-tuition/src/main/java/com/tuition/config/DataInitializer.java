//package com.tuition.config;
//
//import com.tuition.entity.Role;
//import com.tuition.repository.RoleRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataInitializer {
//
//    private final RoleRepository roleRepository;
//
//    public DataInitializer(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    @PostConstruct
//    public void initRoles() {
//
//        if (roleRepository.findByName("ADMIN").isEmpty()) {
//            roleRepository.save(new Role(null, "ADMIN"));
//        }
//
//        if (roleRepository.findByName("TEACHER").isEmpty()) {
//            roleRepository.save(new Role(null, "TEACHER"));
//        }
//
//        if (roleRepository.findByName("STUDENT").isEmpty()) {
//            roleRepository.save(new Role(null, "STUDENT"));
//        }
//    }
//}
