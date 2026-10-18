package com.companny.pinponalv.shoptech.dto;

public record AddressResponse (
         String street,
         String city,
         String state,
         String zip,
         String number,
         String description
){}
