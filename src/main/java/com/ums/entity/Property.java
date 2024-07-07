package com.ums.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "property_name", nullable = false, length = 100)
    private String propertyName;

    @Column(name = "guests", nullable = false)
    private Integer guests;

    @Column(name = "beds", nullable = false)
    private Integer beds;

    @Column(name = "bathrooms", nullable = false)
    private Integer bathrooms;

    @Column(name = "bedrooms", nullable = false)
    private Integer bedrooms;

    @Column(name = "nightly_price", nullable = false)
    private Integer nightlyPrice;

}