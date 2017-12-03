package com.tira.restaurants.dto;

import java.util.Set;

import com.tira.restaurants.domain.Role;

public class UserResponseDTO {

	private Long id;
	private String email;
	private String phone;
	private String country;
	private String city;
	private String firstName;
	private String lastName;
	private Set<String> roles;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserResponseDTO{" +
				"id=" + id +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", country='" + country + '\'' +
				", city='" + city + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", roles=" + roles +
				'}';
	}
}
