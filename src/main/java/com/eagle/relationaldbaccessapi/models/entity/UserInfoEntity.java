package com.eagle.relationaldbaccessapi.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@Column(name = "curp", length = 20, nullable = true)
	private String curp;
	@Column(name = "photo", length = 100, nullable = true)
	private String photoUrl;
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createAt;
	@Column(name = "years_age", nullable = true)
	private Short age;
	@Column(name = "status", nullable = false)
	private Boolean status;

	public UserInfoEntity() {
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getLastName1() {
		return lastName1;
	}

	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}

	public String getLastName2() {
		return lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public static class Builder {
		
		private UserInfoEntity userInfoEntity;
		
		public Builder() {
			this.userInfoEntity = new UserInfoEntity();
			this.userInfoEntity.setStatus(true);
			this.userInfoEntity.setCreateAt(LocalDateTime.now());
		}
		
		public Builder addId(Long id) {
			this.userInfoEntity.setId(id);
			return this;
		}
		
		public Builder addName1(String name1) {
			this.userInfoEntity.setName1(name1);
			return this;
		}
		
		public Builder addName2(String name2) {
			this.userInfoEntity.setName2(name2);
			return this;
		}
		
		public Builder addLastName1(String lastName1) {
			this.userInfoEntity.setLastName1(lastName1);
			return this;
		}
		
		public Builder addLastName2(String lastName2) {
			this.userInfoEntity.setLastName2(lastName2);
			return this;
		}
		
		public Builder addCurp(String curp) {
			this.userInfoEntity.setCurp(curp);
			return this;
		}
		
		public Builder addPhotoUrl(String photoUrl) {
			this.userInfoEntity.setPhotoUrl(photoUrl);
			return this;
		}
		
		public Builder addAge(Short age) {
			this.userInfoEntity.setAge(age);
			return this;
		}
		
		public UserInfoEntity build() {
			return this.userInfoEntity;
		}
	}
}
