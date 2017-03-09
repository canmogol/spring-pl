package com.fererlab.security;

import com.fererlab.security.exception.EmailExistsException;
import com.fererlab.security.model.AuthenticatedUser;
import com.fererlab.security.model.SpringUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    AuthenticatedUser registerNewUserAccount(SpringUser acountDto) throws EmailExistsException;
}
