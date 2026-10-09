package com.companny.pinponalv.shoptech.service;

import com.companny.pinponalv.shoptech.dto.UserRequest;
import com.companny.pinponalv.shoptech.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserSecService {
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(Long id,UserRequest request);
    Page<UserResponse> findAll(Pageable pageable);
    UserResponse findById(Long id);
    void delete(Long id);
    String encriptPassword(String password);
}
