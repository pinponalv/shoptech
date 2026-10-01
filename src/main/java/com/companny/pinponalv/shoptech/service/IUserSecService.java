package com.companny.pinponalv.shoptech.service;

import com.companny.pinponalv.shoptech.dto.UserRequest;
import com.companny.pinponalv.shoptech.dto.UserResponse;

import java.util.List;

public interface IUserSecService {
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(UserRequest request);
    List<UserResponse> findAll();
    UserResponse findById(long id);
    void delete(Long id);
}
