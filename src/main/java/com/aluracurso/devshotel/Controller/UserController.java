package com.aluracurso.devshotel.Controller;

import com.aluracurso.devshotel.Domain.User.DataUser;
import com.aluracurso.devshotel.Domain.User.User;
import com.aluracurso.devshotel.Security.DataJWTToken;
import com.aluracurso.devshotel.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UserController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid DataUser data){
        Authentication auth = new UsernamePasswordAuthenticationToken(data.username(), data.password());

        var userAuth = authManager.authenticate(auth);
        var jwtToken = tService.generateToken((User) userAuth.getPrincipal());

        return ResponseEntity.ok(new DataJWTToken(jwtToken));
    }
}
