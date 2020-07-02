package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDTO implements Serializable{

	private static final long serialVersionUID = -8096606183270497702L;
	
	private Long id;
	@NotNull
	@Size(min = 3,max = 20)
	private String name1;
	@Size(min = 3,max = 20)
	private String name2;
	@NotNull
	private String lastName1;
	@Size(min = 3,max = 20)
	private String lastName2;
	@Size(min = 18, max = 18)
	private String curp;
	private String photoUrl;
	private LocalDateTime createAt;
	private Short age;
	private Boolean status;
	private EmployeeDTO employee;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
