package com.ums.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "guest_name", nullable = false, length = 100)
    private String guestName;

    @Column(name = "total_nights", nullable = false)
    private Integer totalNights;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "check_in", nullable = false)
    private Integer checkIn;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}