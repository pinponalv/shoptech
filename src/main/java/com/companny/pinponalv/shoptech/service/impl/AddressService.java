package com.companny.pinponalv.shoptech.service.impl;

import com.companny.pinponalv.shoptech.dto.AddressRequest;
import com.companny.pinponalv.shoptech.dto.AddressResponse;
import com.companny.pinponalv.shoptech.model.Address;
import com.companny.pinponalv.shoptech.repository.IAddressRepository;
import com.companny.pinponalv.shoptech.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {

    private final IAddressRepository addressRepository;

    @Override
    public AddressResponse addAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setStreet(addressRequest.street());
        address.setCity(addressRequest.city());
        address.setState(addressRequest.state());
        address.setZip(addressRequest.zip());
        address.setNumber(addressRequest.number());
        address.setDescription(addressRequest.description());

        Address savedAddress = addressRepository.save(address);
        return new AddressResponse(
                savedAddress.getStreet(),
                savedAddress.getCity(),
                savedAddress.getState(),
                savedAddress.getZip(),
                savedAddress.getNumber(),
                savedAddress.getDescription()
        );
    }

    @Override
    public AddressResponse updateAddress(Long id, AddressRequest addressRequest) {
        Address address = addressRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Address not found!") );

        if(addressRequest.street() != null){
            address.setStreet(addressRequest.street());
        }
        if(addressRequest.city() != null){
            address.setCity(addressRequest.city());
        }
        if(addressRequest.state() != null){
            address.setState(addressRequest.state());
        }
        if(addressRequest.zip() != null){
            address.setZip(addressRequest.zip());
        }
        if(addressRequest.number() != null){
            address.setNumber(addressRequest.number());
        }
        if(addressRequest.description() != null){
            address.setDescription(addressRequest.description());
        }

        Address savedAddress = addressRepository.save(address);

        return new AddressResponse(
                savedAddress.getStreet(),
                savedAddress.getCity(),
                savedAddress.getState(),
                savedAddress.getZip(),
                savedAddress.getNumber(),
                savedAddress.getDescription()
        );
    }

    @Override
    public List<AddressResponse> getAllAddress() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressResponse> addressResponseList = new ArrayList<>();
        for (Address address : addresses) {
            AddressResponse response = new AddressResponse(
                    address.getStreet(),
                    address.getCity(),
                    address.getState(),
                    address.getZip(),
                    address.getNumber(),
                    address.getDescription()
            );
            addressResponseList.add(response);
        }
        return addressResponseList;
    }

    @Override
    public AddressResponse getAddress(Long id) {
        Address address = addressRepository.findById(id).orElseThrow( () -> new RuntimeException("Address not found!") );

        return new  AddressResponse(
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip(),
                address.getNumber(),
                address.getDescription()
        );
    }

    @Override
    public void deleteAddress(Long id) {
        if(!addressRepository.existsById(id)){
            throw new RuntimeException("Address not found!");
        }
        addressRepository.deleteById(id);
    }
}
