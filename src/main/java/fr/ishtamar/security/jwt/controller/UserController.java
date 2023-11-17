package fr.ishtamar.security.jwt.controller;

import fr.ishtamar.security.jwt.dto.AuthRequest;
import fr.ishtamar.security.jwt.dto.UserDto;
import fr.ishtamar.security.jwt.entity.UserInfo;
import fr.ishtamar.security.jwt.exceptionhandler.EntityNotFoundException;
import fr.ishtamar.security.jwt.mapper.UserMapper;
import fr.ishtamar.security.jwt.service.JwtService;
import fr.ishtamar.security.jwt.service.UserInfoService;
import fr.ishtamar.security.jwt.service.impl.UserInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service=new UserInfoServiceImpl();
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Operation(hidden=true)
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @Operation(summary = "register new user",responses={
            @ApiResponse(responseCode="200", description = "User successfully created"),
            @ApiResponse(responseCode="400", description = "User already exists")
    })
    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @Operation(summary = "generate new JWT",responses={
            @ApiResponse(responseCode="200", description = "Token successfully created"),
            @ApiResponse(responseCode="403", description = "Access unauthorized")
    })
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @Operation(summary = "renew JWT for valid user",responses={
            @ApiResponse(responseCode="200", description = "User successfully created")
    })
    @PostMapping("/renewToken")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public String authenticateAndRenewToken(@RequestHeader(value="Authorization",required = false) String jwt) {
        String username = jwtService.extractUsername(jwt.substring(7));
        return jwtService.generateToken(username);
    }

    @Operation(summary = "gets personal data from user by id",responses={
            @ApiResponse(responseCode="200", description = "Personal data is displayed"),
            @ApiResponse(responseCode="403", description = "Access unauthorized")
    })
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public UserDto getUserById(@PathVariable("id") final long id) throws EntityNotFoundException {
        return userMapper.toDto(service.getUserById(id));
    }
}
