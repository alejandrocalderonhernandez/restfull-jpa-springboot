package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;
import java.time.LocalDateTime;;

public class UserInfoDTO implements Serializable{

	private static final long serialVersionUID = -8096606183270497702L;
	
	private Long id;
	private String name1;
	private String name2;
	private String lastName1;
	private String lastName2;
	private String curp;
	private String photoUrl;
	private LocalDateTime createAt;
	private Short age;
	private Boolean status;
	
    public UserInfoDTO() {
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
		
		private UserInfoDTO userInfoDTO;
		
		public Builder() {
			this.userInfoDTO = new UserInfoDTO();
		}
		
		public Builder addId(Long id) {
			this.userInfoDTO.setId(id);
			return this;
		}
		
		public Builder addName1(String name1) {
			this.userInfoDTO.setName1(name1);
			return this;
		}
		
		public Builder addName2(String name2) {
			this.userInfoDTO.setName2(name2);
			return this;
		}
		
		public Builder addLastName1(String lastName1) {
			this.userInfoDTO.setLastName1(lastName1);
			return this;
		}
		
		public Builder addLastName2(String lastName2) {
			this.userInfoDTO.setLastName2(lastName2);
			return this;
		}
		
		public Builder addCurp(String curp) {
			this.userInfoDTO.setCurp(curp);
			return this;
		}
		
		public Builder addPhotoUrl(String photoUrl) {
			this.userInfoDTO.setPhotoUrl(photoUrl);
			return this;
		}
		
		public Builder addAge(Short age) {
			this.userInfoDTO.setAge(age);
			return this;
		}
		
		public Builder addCreatedAt(LocalDateTime createAt) {
			this.userInfoDTO.setCreateAt(createAt);
			return this;
		}
		
		public Builder addStatus(Boolean status) {
			this.userInfoDTO.setStatus(status);
			return this;
		}
		
		public UserInfoDTO build() {
			return this.userInfoDTO;
		}
	}
}
