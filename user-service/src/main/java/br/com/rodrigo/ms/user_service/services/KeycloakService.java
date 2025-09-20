package br.com.rodrigo.ms.user_service.services;

import java.util.List;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.rodrigo.ms.user_service.dtos.RequestProfileDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@Service
public class KeycloakService {
    

    private final Keycloak keycloak;


    public KeycloakService() {
        this.keycloak = KeycloakBuilder.builder()
        .serverUrl("http://localhost:8080")
        .realm("master")
        .grantType(OAuth2Constants.PASSWORD)
        .clientId("admin-cli")
        .username("admin")
        .password("admin")
        .build();
    }

    public List<UserRepresentation> getUsers() {
        return keycloak.realm("eventify-realm")
                       .users()
                       .list();
    }

    public UserRepresentation getProfile(String userId) {
        UserResource userResource = keycloak
            .realm("eventify-realm")
            .users()
            .get(userId);

        UserRepresentation user = userResource.toRepresentation();
        if (user == null) {
            throw new NotFoundException("User not found!");
        }
        return user;
    }
    
    public UserRepresentation updateProfile(String userId, RequestProfileDTO data) {
        UserResource userResource = keycloak
            .realm("eventify-realm")
            .users()
            .get(userId);

        UserRepresentation user = userResource.toRepresentation();
        if (user == null) {
            throw new NotFoundException("User not found!");
        }

        user.setFirstName(data.firstName());
        user.setLastName(data.lastName());
        user.setEmail(data.email());

        userResource.update(user);

        return user;

    }
    
}
