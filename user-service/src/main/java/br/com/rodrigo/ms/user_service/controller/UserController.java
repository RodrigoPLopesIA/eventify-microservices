package br.com.rodrigo.ms.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigo.ms.user_service.dtos.RequestProfileDTO;
import br.com.rodrigo.ms.user_service.dtos.ResponseAddressDTO;
import br.com.rodrigo.ms.user_service.proxies.CepProxy;
import br.com.rodrigo.ms.user_service.services.KeycloakService;

import java.util.List;

import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/profile")
public class UserController {


    @Autowired
    private KeycloakService keycloakService;


    @GetMapping
    public ResponseEntity<UserRepresentation> index(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        UserRepresentation user = keycloakService.getProfile(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<UserRepresentation> update(@AuthenticationPrincipal Jwt jwt, @RequestBody RequestProfileDTO data) {
        String userId = jwt.getSubject();
        UserRepresentation updateProfile = keycloakService.updateProfile(userId, data);
        return ResponseEntity.ok().body(updateProfile);
    }

    
    
}
