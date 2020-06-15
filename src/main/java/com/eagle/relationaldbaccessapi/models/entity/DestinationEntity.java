package com.eagle.relationaldbaccessapi.models.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name = "DESTINATION")
public class DestinationEntity implements Serializable {

	private static final long serialVersionUID = 8676986431854206227L;
	
	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_alternative", length = 30, nullable = true)
	private String alternativeId;
	@Column(name = "name_destination", length = 30, nullable = false)
	private String name;
	@Column(name = "type_destination", nullable = false)
	private 	Short type;
	@Column(name = "final_destination", nullable = false)
	private Boolean finalDestination;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_address")
	private AddressEntity addess;
	
	@ManyToMany(mappedBy = "destinations", cascade = CascadeType.ALL)
	private Set<RouteEntity> routes;
	
	public DestinationEntity() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAlternativeId() {
		return alternativeId;
	}
	
	public void setAlternativeId(String alternativeId) {
		this.alternativeId = alternativeId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Short getType() {
		return type;
	}
	
	public void setType(Short type) {
		this.type = type;
	}
	
	public Boolean isFinalDestination() {
		return finalDestination;
	}
	
	public void setFinalDestination(Boolean finalDestination) {
		this.finalDestination = finalDestination;
	}
	
	public AddressEntity getAddess() {
		return addess;
	}
	
	public void setAddess(AddressEntity addess) {
		this.addess = addess;
	}
	
	public Set<RouteEntity> getRoutes() {
		return routes;
	}
	
	public void setRoutes(Set<RouteEntity> routes) {
		this.routes = routes;
	}
	
	@Override
	public String toString() {
		return "DestinationDTO: ".concat(new Gson().toJson(this));
	}
	
}
