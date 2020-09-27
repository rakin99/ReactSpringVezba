package com.example.bioskop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bioskop.model.Authority;
import com.example.bioskop.repository.AuthorityRepository;

@Service
public class AuthorityService implements AuthorityServiceInterface {

  @Autowired
  private AuthorityRepository authorityRepository;

  @Override
  public List<Authority> findById(Long id) {
    Authority auth = this.authorityRepository.getOne(id);
    List<Authority> auths = new ArrayList<>();
    auths.add(auth);
    return auths;
  }

  @Override
  public List<Authority> findByName(String name) {
	String[] a = name.split(" ");
	List<Authority> auths = new ArrayList<>();
	for (String s : a) {
		Authority auth = this.authorityRepository.findByName(s);
	    auths.add(auth);
	}
    return auths;
  }


}
