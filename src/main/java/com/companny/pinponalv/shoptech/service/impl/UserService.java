package com.companny.pinponalv.shoptech.service.impl;

import com.companny.pinponalv.shoptech.dto.RoleIdRequest;
import com.companny.pinponalv.shoptech.dto.RoleResponse;
import com.companny.pinponalv.shoptech.dto.UserRequest;
import com.companny.pinponalv.shoptech.dto.UserResponse;
import com.companny.pinponalv.shoptech.mapper.UserMapper;
import com.companny.pinponalv.shoptech.model.Roles;
import com.companny.pinponalv.shoptech.model.UserSec;
import com.companny.pinponalv.shoptech.repository.RolesRepository;
import com.companny.pinponalv.shoptech.repository.UserSecRepository;
import com.companny.pinponalv.shoptech.service.IUserSecService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements IUserSecService {
    private final UserSecRepository userSecRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    //TODO: Cambiar la excepcion
    @Override
    public UserResponse createUser(UserRequest request) {
        validateUser(request);


        Roles role = rolesRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("role not found"));

        UserSec user = new UserSec();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setNumberPhone(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setPassword(encriptPassword(request.getPassword()));
        user.getRoles().add(role);

        return userMapper.toResponse(user);
    }

    //TODO: CAMBIAR LA EXCEPCION
    @Override
    public UserResponse updateUser(Long id,UserRequest request) {
        UserSec user = userSecRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));

        if(request.getName() != null){
            user.setName(request.getName());
        }
        if(request.getLastName() != null){
            user.setLastName(request.getLastName());
        }
        if(request.getPhoneNumber() != null){
            user.setNumberPhone(request.getPhoneNumber());
        }
        if(request.getEmail() != null){
            user.setEmail(request.getEmail());
        }
        if(request.getPassword() != null){
            user.setPassword(encriptPassword(request.getPassword()));
        }
        if(request.getRoles() != null){
            user.setRoles(resolveRoles(request.getRoles()));
        }

        UserSec updatedUser = userSecRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<UserSec> userSecs = userSecRepository.findAll(pageable);
        List<UserResponse> UserResponses = new ArrayList<>();

        for(UserSec userSec : userSecs.getContent()){
            UserResponse userResponse = userMapper.toResponse(userSec);
            UserResponses.add(userResponse);
        }
        return new PageImpl<>(UserResponses, pageable, userSecs.getTotalElements());
    }

    //TODO: CAMBIAR EXCEPCION
    @Override
    public UserResponse findById(Long id) {
        UserSec user = userSecRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));
        return userMapper.toResponse(user);
    }

    //TODO: CAMBIAR EXCEPCION
    @Override
    public void delete(Long id) {
        if(!userSecRepository.existsById(id)){
            throw new RuntimeException("user not found");
        }
        userSecRepository.deleteById(id);
    }

    @Override
    public String encriptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private Set<Roles> resolveRoles(Set<RoleIdRequest> roleIdRequests) {
        Set<Roles> roles = new HashSet<>();
        for(RoleIdRequest role: roleIdRequests){
            Roles readRole = rolesRepository.findById(role.getId())
                    .orElseThrow(() -> new RuntimeException("Role Not Found"));
            roles.add(readRole);
        }
        return roles;
    }

    //TODO: cambiar esta excepcion
    private void validateUser(UserRequest userRequest) {
        if(userSecRepository.existsByEmail(userRequest.getEmail())){
            throw new RuntimeException("Email Already Exists");
        }
    }

}
