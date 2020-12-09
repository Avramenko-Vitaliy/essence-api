package com.essence.api.services;

import com.essence.api.auth.tokens.AppToken;
import com.essence.api.dtos.Credentials;
import com.essence.api.entities.User;
import com.essence.api.entities.security.Permission;
import com.essence.api.repositories.PermissionsRepository;
import com.essence.api.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoginService {

    private final UsersRepository usersRepository;
    private final PermissionsRepository permissionsRepository;
    private final PasswordEncoder passwordDecoder;

    public AppToken login(Credentials credentials) {
        User user = usersRepository.findByEmailIgnoreCase(credentials.getEmail())
                .filter(item -> passwordDecoder.matches(credentials.getPassword(), item.getPassword()))
                .orElseThrow(() -> new BadCredentialsException("Unknown user"));

        List<Permission> permissions = permissionsRepository.findPermissionsByRoleId(user.getRoleId());
        Set<Permission.Type> tokenPermissions = permissions.stream().map(Permission::getApiKey).collect(Collectors.toSet());
        return AppToken.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .permissions(tokenPermissions)
                .xsrfToken(UUID.randomUUID().toString())
                .build();
    }
}
