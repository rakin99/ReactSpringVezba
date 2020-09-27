package com.example.bioskop.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bioskop.ModelDTO.UserDTO;
import com.example.bioskop.model.Authority;
import com.example.bioskop.model.User;
import com.example.bioskop.service.AuthorityService;
import com.example.bioskop.service.UserService;
import com.example.bioskop.tools.DateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/bioskop")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@GetMapping(value = "/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> getUsers() throws ParseException{
		System.out.println("\n\n\t------------->Get users<--------------");
		
		List<User> users=userService.findAll();
		List<UserDTO> activeUsers=new ArrayList<UserDTO>();
		for (User user : users) {
			if(user.isEnabled()) {
				activeUsers.add(new UserDTO(user));
				System.out.println(new UserDTO(user).toString());
			}
		}
		
		return ResponseEntity.ok().body(activeUsers);
	}
	
	@GetMapping(value = "/users/{korisnicko}/{uloga}/{sort}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<UserDTO>> getUsersByUsername(@PathVariable("korisnicko")String korisnicko, @PathVariable("uloga")String uloga,@PathVariable("sort")String s) throws ParseException{
		System.out.println("\n\n\t------------->Get users filter<--------------");

		String username="";
		String role="";
		String sort="";
		if(!korisnicko.equals("null")) {
			username=korisnicko;
		}
		if(!uloga.equals("null")) {
			role=uloga;
		}
		if(!s.equals("null")) {
			sort=s;
		}
		List<User> users=userService.findAll();
		List<UserDTO> activeUsers=new ArrayList<UserDTO>();
		for (User user : users) {
			if(user.isEnabled()) {
				if(user.getAuthoritiesAsString().equals(role)) {
					if(user.getUsername().toLowerCase().contains(username.toLowerCase())) {
						activeUsers.add(new UserDTO(user));
					}
				}else if(role.equals("")) {
					if(user.getUsername().toLowerCase().contains(username.toLowerCase())) {
						activeUsers.add(new UserDTO(user));
					}
				}
			}
		}
		
		switch (sort) {
		case "ki-asc":
			System.out.println(sort);
			activeUsers.sort(Comparator.comparing(UserDTO::getKorisnickoIme,String.CASE_INSENSITIVE_ORDER));
			break;
			
		case "ki-desc":
			System.out.println(sort);
			activeUsers.sort(Comparator.comparing(UserDTO::getKorisnickoIme,String.CASE_INSENSITIVE_ORDER).reversed());
			break;
			
		case "u-asc":
			System.out.println(sort);
			activeUsers.sort(Comparator.comparing(UserDTO::getAuthorities).reversed());
			break;
			
		case "u-desc":
			System.out.println(sort);
			activeUsers.sort(Comparator.comparing(UserDTO::getAuthorities));
			break;

		default:
			break;
		}
		
		return ResponseEntity.ok().body(activeUsers);
	}
	
	@PutMapping(value = "/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> editUser(@RequestBody String s) throws ParseException{
		System.out.println("\n\n\t------------>Ulazim u put users<------------------");
		
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(s);// response will be the json String
		UserDTO userDTO = gson.fromJson(object, UserDTO.class);
		
		User user=userService.findByUsername(userDTO.getKorisnickoIme());
		user.setAuthorities(authorityService.findByName(userDTO.getAuthorities()));
		String pass=user.getPassword();
		if(userDTO.getPassword()!=null){
			pass=userDTO.getPassword();
		}
		user.setPassword(pass);
		user=userService.save(user);
		
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@DeleteMapping(value = "/users/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String s){
		
		System.out.println("\n\n\t------------>Ulazim u delete users<------------------");
		
		User user=userService.findByUsername(s);
		user.setEnable(false);
		userService.save(user);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
