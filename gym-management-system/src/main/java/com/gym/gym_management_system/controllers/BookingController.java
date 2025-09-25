package com.gym.gym_management_system.controllers;

import com.gym.gym_management_system.dtos.BookingRequestDto;
import com.gym.gym_management_system.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Void> createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        bookingService.bookClass(
                bookingRequestDto.getMemberId(),
                bookingRequestDto.getScheduledClassId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}