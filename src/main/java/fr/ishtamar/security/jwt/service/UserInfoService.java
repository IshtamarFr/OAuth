package fr.ishtamar.security.jwt.service;

import fr.ishtamar.security.jwt.entity.UserInfo;
import fr.ishtamar.security.jwt.exceptionhandler.EmailAlreadyUsedException;
import fr.ishtamar.security.jwt.exceptionhandler.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserInfoService extends UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws EntityNotFoundException;

    public String addUser(UserInfo userInfo) throws EmailAlreadyUsedException;

    public UserInfo getUserByUsername(String username) throws EntityNotFoundException;

    public UserInfo getUserById(Long id) throws EntityNotFoundException;
}
