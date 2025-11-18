package com.hades.hadescoffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@SpringBootApplication
public class HadesCoffeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(HadesCoffeeApplication.class, args);
    }

//    @Bean
//    CommandLineRunner init(RolesRepository rolesRepository,
//                           UsersRepository usersRepository) {
//        return args -> {
//            rolesRepository.save(new Role("USER", "User"));
//            rolesRepository.save(new Role("ADMIN", "Administrator"));
//            rolesRepository.save(new Role("CASHER", "Casher"));
//            rolesRepository.save(new Role("KITCHEN", "Kitchen"));
//        };
//    }
}
