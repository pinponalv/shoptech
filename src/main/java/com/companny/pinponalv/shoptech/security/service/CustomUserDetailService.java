package com.companny.pinponalv.shoptech.security.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.companny.pinponalv.shoptech.dto.AuthLoginRequest;
import com.companny.pinponalv.shoptech.dto.AuthResponse;
import com.companny.pinponalv.shoptech.model.RefreshToken;
import com.companny.pinponalv.shoptech.model.UserSec;
import com.companny.pinponalv.shoptech.repository.RefreshTokenRepository;
import com.companny.pinponalv.shoptech.repository.UserSecRepository;
import com.companny.pinponalv.shoptech.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserSecRepository userSecRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;


    //TODO: TERMINAR DE CONFIGURAR ESTA PARTE
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserSec user = userSecRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();

        //Obtengo roles del usuario y los convierto a una simplegrantedauthority
        user.getRoles()
                .forEach(role -> authoritiesList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));


        /**
         * traer permisos y convertirlos en SimpleGrantedAuthority
         * a la lista de roles lo transformo en un stream para usar flatmap que permite hacer un mapeo de lista de permisos
         * que tienen el rol y al mismo tiempo las transformo a un stream para usar el forEach
         * que por cada permiso que encuentre lo agregue al authorityList haciendo una conversion a
         * SimpleGrantedAuthority apartir del nombre del permiso que trae
         * **/
        user.getRoles().stream().flatMap(role -> role.getPermissionsList()
                .stream()).forEach(permission -> authoritiesList
                .add(new SimpleGrantedAuthority(permission.getPermissionName())));

        //retorno el usuario en formato user de spring security
        return new User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                authoritiesList
        );
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);

        if(userDetails == null) {
            throw new UsernameNotFoundException("Invalid email o password");
        }else if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        //no retorno la password
        return new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String email = authLoginRequest.getEmail();
        String password = authLoginRequest.getPassword();

        Authentication authentication = authenticate(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.generateAccessToken(authentication);
        String refreshToken = jwtUtils.generateRefreshToken(authentication);

        DecodedJWT decodedJWT = jwtUtils.verifyToken(refreshToken);
        UserSec user = userSecRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        RefreshToken entity = new RefreshToken();
        entity.setId(decodedJWT.getId());//UUID que genere en jwtUtils, decoded recibe string solamente
        entity.setUserSec(user);
        entity.setExpireAt(LocalDateTime.now().plusDays(7));
        refreshTokenRepository.save(entity);

        AuthResponse response = new AuthResponse(email, "Login successful", accessToken, refreshToken, true);
        return response;
    }

    public AuthResponse refresToken(String refreshToken) {
        //verifico que sea un json web token valido
        DecodedJWT decodedJWT = jwtUtils.verifyToken(refreshToken);


        //extraigo el "type" que guardamos al crear el token (access/refresh)
        String type = jwtUtils.getSpecificClaim(decodedJWT, "type").asString();
        if(!"refresh".equals(type)) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        //nuevo
        RefreshToken stored = refreshTokenRepository.findById(decodedJWT.getId())
                .orElseThrow(() -> new BadCredentialsException("refresh token not recognized"));
        if(stored.isRevoked()) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        String email = jwtUtils.extractEmailOfToken(decodedJWT);
        UserDetails userDetails = loadUserByUsername(email); //cargo el usuario de nuevo desde la bd actual


        /*armo un objeto de authentication
        * (no pasa por login con password, porque nolo necesitamos, ya confiamos en el refreshToken validado)
        * NOTA: recordamos que el parametro null, es la password que no se aplica aqui*/
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());

        String newAccesToken = jwtUtils.generateAccessToken(authentication);

        //el refreshToken sigue siendo el original no se genera uno nuevo
        return new AuthResponse(email, "Token refreshed", newAccesToken, refreshToken, true);
    }

    public void revokeRefreshToken(String refreshToken) {
        DecodedJWT decodedJWT = jwtUtils.verifyToken(refreshToken);
        RefreshToken stored = refreshTokenRepository.findById(decodedJWT.getId())
                .orElseThrow(() -> new BadCredentialsException("Invalid refresh token"));

        stored.setRevoked(true);
        refreshTokenRepository.save(stored);
    }
}
