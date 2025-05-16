package futurodevv1.reciclaville.configs;

import futurodevv1.reciclaville.entities.User;
import futurodevv1.reciclaville.enums.UserRole;
import futurodevv1.reciclaville.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer
{
    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder encoder)
    {
        return args ->
        {
            if (!userRepository.existsByUsername("admin"))
            {
                User admin = User.builder()
                        .name("Administrador")
                        .username("admin")
                        .password(encoder.encode("admin"))
                        .role(UserRole.ADMIN)
                        .build();
                userRepository.save(admin);
            }
        };
    }
}
