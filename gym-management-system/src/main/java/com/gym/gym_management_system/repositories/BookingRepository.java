// BookingRepository.java
package com.gym.gym_management_system.repositories;
import com.gym.gym_management_system.entities.Booking;
import com.gym.gym_management_system.entities.ScheduledClass;
import com.gym.gym_management_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByMemberAndScheduledClass(User member, ScheduledClass scheduledClass);
    long countByScheduledClass(ScheduledClass scheduledClass);
}