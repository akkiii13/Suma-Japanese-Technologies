package com.sjtxev.sjt_spring_security.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class User1Dto {

	private Long id;

	@NotBlank(message = "Please enter username")
	private String username;

	@NotBlank(message = "Please enter password")
	private String password;

}
