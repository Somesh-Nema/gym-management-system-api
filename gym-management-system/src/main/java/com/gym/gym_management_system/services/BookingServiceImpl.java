package com.gym.gym_management_system.services;

import com.gym.gym_management_system.entities.Booking;
import com.gym.gym_management_system.entities.ScheduledClass;
import com.gym.gym_management_system.entities.User;
import com.gym.gym_management_system.exceptions.BookingException;
import com.gym.gym_management_system.exceptions.ResourceNotFoundException;
import com.gym.gym_management_system.repositories.BookingRepository;
import com.gym.gym_management_system.repositories.ScheduledClassRepository;
import com.gym.gym_management_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ScheduledClassRepository scheduledClassRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository, ScheduledClassRepository scheduledClassRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.scheduledClassRepository = scheduledClassRepository;
    }

    @Override
    @Transactional
    public void bookClass(Long memberId, Long scheduledClassId) {
        User member = userRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + memberId));

        ScheduledClass scheduledClass = scheduledClassRepository.findById(scheduledClassId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + scheduledClassId));

        if (!member.getRole().equals(User.Role.MEMBER)) {
            throw new BookingException("Only members can book classes.");
        }

        if (bookingRepository.existsByMemberAndScheduledClass(member, scheduledClass)) {
            throw new BookingException("Member has already booked this class.");
        }

        long currentBookings = bookingRepository.countByScheduledClass(scheduledClass);
        if (currentBookings >= scheduledClass.getCapacity()) {
            throw new BookingException("Class is already full.");
        }

        Booking newBooking = new Booking();
        newBooking.setMember(member);
        newBooking.setScheduledClass(scheduledClass);

        bookingRepository.save(newBooking);
    }
}