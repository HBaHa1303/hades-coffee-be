package com.hades.hadescoffee;

import com.hades.hadescoffee.domain.models.Role;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.models.UserRole;
import com.hades.hadescoffee.domain.repositories.RolesRepository;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@SpringBootApplication
public class HadesCoffeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(HadesCoffeeApplication.class, args);
    }

    //TODO: add api change password
    //TODO: add api cancel order for user
//
//    @Bean
//    CommandLineRunner init(RolesRepository rolesRepository,
//                           UsersRepository usersRepository) {
//        return args -> {
//            User user = new User(null, "system@gmail.com", "Ha@123456789", "system", "system", LocalDate.now(), Set.of());
//            user.setCreatedBy(1L);
//            user.setLastModifiedBy(1L);
//            usersRepository.save(user);
//
//            rolesRepository.save(new Role("USER", "User"));
//            rolesRepository.save(new Role("ADMIN", "Administrator"));
//            rolesRepository.save(new Role("CASHER", "Casher"));
//            rolesRepository.save(new Role("KITCHEN", "Kitchen"));
//        };
//    }
}
