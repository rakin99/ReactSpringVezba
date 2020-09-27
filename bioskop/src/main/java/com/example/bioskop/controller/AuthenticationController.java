package com.example.bioskop.controller;


import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bioskop.ModelDTO.FilmDTO;
import com.example.bioskop.ModelDTO.UserDTO;
import com.example.bioskop.auth.JwtAuthenticationRequest;
import com.example.bioskop.model.User;
import com.example.bioskop.model.UserTokenState;
import com.example.bioskop.repository.UserRepository;
import com.example.bioskop.security.TokenUtils;
import com.example.bioskop.service.AuthorityService;
import com.example.bioskop.service.CustomUserDetailsService;

import com.example.bioskop.service.UserService;
import com.example.bioskop.tools.DateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping(value = "/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody String s) {
		System.out.println("Ulazim u login");
		
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(s);// response will be the json String
		JwtAuthenticationRequest authenticationRequest = gson.fromJson(object, JwtAuthenticationRequest.class);
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername(), user.getAuthoritiesAsString());
		int expiresIn = tokenUtils.getExpiredIn();
		System.out.println("\n\n\t----------->Zavrsio se login!");
		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}
	
	// Endpoint za registraciju novog korisnika
	@PostMapping(value = "/signup")
	public ResponseEntity<Map<String, String>> newUser(@RequestBody String s) throws ParseException{
		System.out.println("\n\n\t------------>Ulazim u post users<------------------");
		System.out.println("User string: "+s);
		Map<String, String> result = new HashMap<>();
		String r="";
		try {
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonObject object = (JsonObject) parser.parse(s);// response will be the json String
			UserDTO userDTO = gson.fromJson(object, UserDTO.class);
			
			User user = new User();
			user.setUsername(userDTO.getKorisnickoIme());
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			user.setDatumRegistracije(DateUtil.convertFromDMY((userDTO.getDatumRegistracije())));
			user.setAuthorities(authorityService.findByName("ROLE_USER"));
			user.setEnable(true);
			
			user= userService.save(user);
			r="success";
			
			result.put("result",r);
		} catch (Exception e) {
			r="error";
			
			result.put("result",r);
		}
		
		return ResponseEntity.accepted().body(result);
	}
	
	// U slucaju isteka vazenja JWT tokena, endpoint koji se poziva da se token osvezi
		@PostMapping(value = "/refresh")
		public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {

			String token = tokenUtils.getToken(request);
			String email = this.tokenUtils.getEmailFromToken(token);
			User user = (User) this.userDetailsService.loadUserByUsername(email);

			if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
				String refreshedToken = tokenUtils.refreshToken(token);
				int expiresIn = tokenUtils.getExpiredIn();

				return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
			} else {
				UserTokenState userTokenState = new UserTokenState();
				return ResponseEntity.badRequest().body(userTokenState);
			}
		}

		@RequestMapping(value = "/change-password", method = RequestMethod.POST)
		@PreAuthorize("hasRole('USER')")
		public ResponseEntity<?> changePassword(@RequestBody String s) {
			System.out.println("\nUlazim u changePassword\n");
			
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonObject object = (JsonObject) parser.parse(s);// response will be the json String
			PasswordChanger passwordChanger = gson.fromJson(object, PasswordChanger.class);
			
			userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

			Map<String, String> result = new HashMap<String, String>();
			result.put("result", "success");
			return ResponseEntity.accepted().body(result);
		}

		static class PasswordChanger {
			public String oldPassword;
			public String newPassword;
		}
}
