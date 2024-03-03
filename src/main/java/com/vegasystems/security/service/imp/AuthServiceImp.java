package com.vegasystems.security.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.vegasystems.entity.Rol;
import com.vegasystems.entity.Usuario;
import com.vegasystems.repository.IRolRepository;
import com.vegasystems.repository.IUsuarioRepository;
import com.vegasystems.security.dto.AuthResponse;
import com.vegasystems.security.dto.AuthenticationRequest;
import com.vegasystems.security.dto.RegisterRequest;
import com.vegasystems.security.service.AuthService;
import com.vegasystems.security.service.JwtService;
import com.vegasystems.snums.TipoRol;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{
	private final PasswordEncoder passwordEncoder;
	private final IUsuarioRepository usuarioRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final IRolRepository rolRepository;
	
	@Override
	public AuthResponse register(RegisterRequest request) {
		List<Rol> roles = new ArrayList<>();
		Optional<Rol> rol = rolRepository.findByNombreRol(TipoRol.ROLE_USER);
		if(rol.isPresent()) {
			roles.add(rol.get());
		}
		
		var user = Usuario.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.roles(roles)
				.build();
		
		usuarioRepository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwtToken).build();
	}

	@Override
	public AuthResponse authenticate(AuthenticationRequest request) {
		System.out.println("Autenticación");
		System.out.println(request.getEmail());
		System.out.println(request.getPassword());
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwtToken).build();
	}

}

