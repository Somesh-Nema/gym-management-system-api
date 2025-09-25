// UserRepository.java
package com.gym.gym_management_system.repositories;
import com.gym.gym_management_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}