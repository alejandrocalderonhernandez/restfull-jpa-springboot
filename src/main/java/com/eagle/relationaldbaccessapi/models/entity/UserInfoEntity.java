package com.eagle.relationaldbaccessapi.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER_INFO")
public class UserInfoEntity implements Serializable{

	private static final long serialVersionUID = -6742470073798537394L;
	
	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_1", length = 20, nullable = false)
	private String name1;
	
	@Column(name = "name_2", length = 20, nullable = true)
	private String name2;
	@Column(name = "lastname_1", length = 20, nullable = false)
	private String lastName1;
	@Column(name = "lastname_2", length = 20, nullable = true)
	private String lastName2;
	@Column(name = "curp", length = 18, nullable = true)
	private String curp;
	@Column(name = "photo", length = 100, nullable = true)
	private String photoUrl;
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createAt;
	@Column(name = "years_age", nullable = true)
	private Short age;
	@Column(name = "status", nullable = false)
	private Boolean status;
	
	@OneToOne(mappedBy = "userInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private EmployeeEntity employee;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
