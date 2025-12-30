package com.icement.api.iCement.Domains.Base.Entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Embeddable
public class Address {
    private String name;
    private String street;
    private String city;
    private String region;
    private String postalCode;
    private String digitalAddress;
    private String country;
    private String phoneNumber;
}
