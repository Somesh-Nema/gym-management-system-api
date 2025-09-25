// MembershipPlanRepository.java
package com.gym.gym_management_system.repositories;
import com.gym.gym_management_system.entities.MembershipPlan;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, Long> {}