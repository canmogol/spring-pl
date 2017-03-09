package com.fererlab.security;

import com.fererlab.model.User;
import com.fererlab.security.exception.EmailExistsException;
import com.fererlab.security.model.AuthenticatedUser;
import com.fererlab.security.model.SpringUser;
import com.fererlab.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserRepositoryUserDetailsService implements UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthenticatedUser registerNewUserAccount(SpringUser serviceUser) throws EmailExistsException {

        if (emailExists(serviceUser.getEmail())) {
            throw new EmailExistsException("There is an account with this email: " + serviceUser.getEmail());
        }

        User user = new User();
        user.setFirstName(serviceUser.getFirstName());
        user.setLastName(serviceUser.getLastName());
        user.setPassword(passwordEncoder.encode(serviceUser.getPassword()));
        user.setEmail(serviceUser.getEmail());
        if (userRepository.save(user) != null) {
            AuthenticatedUser authenticatedUser = new AuthenticatedUser(user);
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    authenticatedUser,
                    null,
                    new ArrayList<GrantedAuthority>()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            return authenticatedUser;
        }
        return null;
    }

    private boolean emailExists(String email) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user " + username);
        }
        return new AuthenticatedUser(user);
    }

}
