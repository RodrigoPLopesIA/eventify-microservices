package br.com.rodrigo.ms.user_service.services;

import java.util.List;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import br.com.rodrigo.ms.user_service.dtos.RequestProfileDTO;
import jakarta.persistence.EntityNotFoundException;

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

    public UserRepresentation getProfile(String username) {
        List<UserRepresentation> users = keycloak
        .realm("eventify-realm")
        .users()
        .search(username, true);

        if (users == null && users.isEmpty()) {
            throw new EntityNotFoundException("User not found!");
        }
        return users.get(0);
    }
    
    public void updateProfile(String username, RequestProfileDTO data) {
        List<UserRepresentation> users = keycloak
            .realm("eventify-realm")
            .users()
            .search(username, true);

        if (users == null || users.isEmpty()) {
            throw new EntityNotFoundException("User not found!");
        }

        UserRepresentation user = users.get(0);
        user.setFirstName(data.firstName());
        user.setLastName(data.lastName());
        user.setEmail(data.email());

        UserResource userResource = keycloak
            .realm("eventify-realm")
            .users()
            .get(user.getId());

        userResource.update(user);
    }
}
