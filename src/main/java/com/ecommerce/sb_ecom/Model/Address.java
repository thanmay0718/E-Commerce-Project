package com.ecommerce.sb_ecom.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name must be at-least of 5 characters")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name must be at-least of 5 characters")
    private String buildingName;

    @NotBlank
    @Size(min = 5, message = "City name must be at-least of 4 characters")
    private String cityName;

    @NotBlank
    @Size(min = 5, message = "State name must be at-least of 2 characters")
    private String state;

    @NotBlank
    @Size(min = 5, message = "Country name must be at-least of 2 characters")
    private String country;

    @NotBlank
    @Size(min = 6, message = "Pin code must be at-least of 5 characters")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

    public Address(String street, String buildingName, String cityName, String state, String country, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.cityName = cityName;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
