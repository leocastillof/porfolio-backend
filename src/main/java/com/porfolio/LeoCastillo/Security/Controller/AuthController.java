package com.porfolio.LeoCastillo.Security.Controller;

import com.porfolio.LeoCastillo.Enums.RolName;
import com.porfolio.LeoCastillo.Security.Dto.JwtDto;
import com.porfolio.LeoCastillo.Security.Dto.LoginUser;
import com.porfolio.LeoCastillo.Security.Dto.NewUser;
import com.porfolio.LeoCastillo.Security.Entity.Rol;
import com.porfolio.LeoCastillo.Security.Entity.User;
import com.porfolio.LeoCastillo.Security.Service.RolServices;
import com.porfolio.LeoCastillo.Security.Service.UserServices;
import com.porfolio.LeoCastillo.Security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserServices userService;
    @Autowired
    RolServices rolService;
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/new")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Las credenciales no son válidas"), HttpStatus.BAD_REQUEST);
        if(userService.existsByUsername(newUser.getUsername()))
            return new ResponseEntity(new Message("El nombre de usuario ya está en uso"), HttpStatus.BAD_REQUEST);
        if(userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Message("El correo electrónico ya está en uso"), HttpStatus.BAD_REQUEST);      
        User user = new User(newUser.getName(), newUser.getUsername(),
        newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByName(RolName.ROLE_USER).get());
        
        if(newUser.getRoles().contains("admin"))
            roles.add( rolService.getByName(RolName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        
        return new ResponseEntity(new Message("Usuario guardado"), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Campos no válidos"), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginUser.getUsername(), loginUser.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtProvider.generateToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
