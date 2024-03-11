package com.simpletask.simplemeal.dto;

import com.simpletask.simplemeal.annotations.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

	  	@NotBlank(message = "firstName is required")
	    private String firstName;
	  	
	  	@NotBlank(message = "lastName is required")
	    private String lastName;

	    @NotBlank(message = "Email is required")
	    @Email(message = "wrong email format")
	    @UniqueEmail
	    private String email;

	    @NotBlank(message = "Password is required")
	    @Size(min = 8,message= "Password must have  8+ characters")
	    private String password;
	    
	    

		public UserDTO(@NotBlank(message = "firstName is required") String firstName,
				@NotBlank(message = "lastName is required") String lastName,
				@NotBlank(message = "Email is required") @Email(message = "wrong email format") String email,
				@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must have  8+ characters") String password) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
		}
		
		public UserDTO() {}

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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
					+ password + "]";
		}
	    
	    

	}


