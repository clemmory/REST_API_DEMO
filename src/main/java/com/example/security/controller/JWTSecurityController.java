package com.example.security.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.JwtGenerador;
import com.example.security.dto.DtoAuthRespuesta;
import com.example.security.dto.DtoLogin;
import com.example.security.dto.DtoRegistro;
import com.example.security.entities.MyUser;
import com.example.security.entities.Role;
import com.example.security.repositories.RoleRepository;
import com.example.security.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class JWTSecurityController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository usuariosRepository;
    private final JwtGenerador jwtGenerador;


    //Método para poder registrar usuarios con role "user"
    @PostMapping("register")
    public ResponseEntity<String> registrar(@RequestBody DtoRegistro dtoRegistro) {
        if (usuariosRepository.existsByUserName(dtoRegistro.getUsername())) {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        MyUser usuarios = new MyUser();
        usuarios.setUserName(dtoRegistro.getUsername());
        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Role role = roleRepository.findByRole("USER").get();
        usuarios.setRoles(Collections.singletonList(role));
        usuariosRepository.save(usuarios);
        return new ResponseEntity<>("Registro de usuario exitoso", HttpStatus.OK);
    }

    //Método para poder guardar usuarios de tipo ADMIN
    @PostMapping("registerAdm")
    public ResponseEntity<String> registrarAdmin(@RequestBody DtoRegistro dtoRegistro) {
        if (usuariosRepository.existsByUserName(dtoRegistro.getUsername())) {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        MyUser usuarios = new MyUser();
        usuarios.setUserName(dtoRegistro.getUsername());
        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Role role = roleRepository.findByRole("ADMIN").get();
        usuarios.setRoles(Collections.singletonList(role));
        usuariosRepository.save(usuarios);
        return new ResponseEntity<>("Registro de admin exitoso", HttpStatus.OK);
    }

    //Método para poder logear un usuario y obtener un token
    @PostMapping("login")
    public ResponseEntity<DtoAuthRespuesta> login(@RequestBody DtoLogin dtoLogin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dtoLogin.getUsername(), dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerador.generarToken(authentication);
        return new ResponseEntity<>(new DtoAuthRespuesta(token), HttpStatus.OK);
    }
}