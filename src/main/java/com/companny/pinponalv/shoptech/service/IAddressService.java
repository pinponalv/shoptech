package com.companny.pinponalv.shoptech.service;

import com.companny.pinponalv.shoptech.dto.AddressRequest;
import com.companny.pinponalv.shoptech.dto.AddressResponse;

import java.util.List;

public interface IAddressService {
    AddressResponse addAddress(AddressRequest addressRequest);
    AddressResponse updateAddress(Long id,AddressRequest addressRequest);
    List<AddressResponse> getAllAddress();
    AddressResponse getAddress(Long id);
    void deleteAddress(Long id);
}
