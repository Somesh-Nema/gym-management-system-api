// ScheduledClassRepository.java
package com.gym.gym_management_system.repositories;
import com.gym.gym_management_system.entities.ScheduledClass;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ScheduledClassRepository extends JpaRepository<ScheduledClass, Long> {
    List<ScheduledClass> findAllByOrderByStartTimeAsc();
}