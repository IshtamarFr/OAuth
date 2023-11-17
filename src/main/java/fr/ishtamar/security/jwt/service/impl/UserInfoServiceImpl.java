package fr.ishtamar.security.jwt.service.impl;

import fr.ishtamar.security.jwt.entity.UserInfo;
import fr.ishtamar.security.jwt.exceptionhandler.EmailAlreadyUsedException;
import fr.ishtamar.security.jwt.exceptionhandler.EntityNotFoundException;
import fr.ishtamar.security.jwt.repository.UserInfoRepository;
import fr.ishtamar.security.jwt.service.UserInfoDetails;
import fr.ishtamar.security.jwt.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws EntityNotFoundException {
        //[X-01] Choose findByName or findByEmail
        Optional<UserInfo> userDetail = repository.findByEmail(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new EntityNotFoundException(UserDetails.class,"username",username));
    }

    @Override
    public String addUser(UserInfo userInfo) throws EmailAlreadyUsedException {
        Optional<UserInfo> userDetail = repository.findByEmail(userInfo.getEmail());
        if (userDetail.isPresent()){
            throw new EmailAlreadyUsedException();
        } else {
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
            repository.save(userInfo);
            return "User Added Successfully";
        }
    }

    @Override
    public UserInfo getUserByUsername(String username) throws EntityNotFoundException {
        //[X-01] Choose findByName or findByEmail
        return repository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException(UserInfo.class,"email",username));
    }

    @Override
    public UserInfo getUserById(Long id) throws EntityNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UserDetails.class,"id",id.toString()));
    }
}
