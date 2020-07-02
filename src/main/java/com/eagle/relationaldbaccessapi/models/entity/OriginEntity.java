package com.eagle.relationaldbaccessapi.models.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "ORIGIN")
public class OriginEntity implements Serializable{

	private static final long serialVersionUID = 4754407946803307618L;
	
	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_alternative", length = 30, nullable = true)
	private String alternativeId;
	@Column(name = "name_origin", length = 30, nullable = false)
	private String name;
	@Column(name = "type_origin", nullable = false)
	private 	Short type;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_address")
	private AddressEntity address;
	
	@OneToMany(
			mappedBy = "origin", 
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private Set<RouteEntity> routes = new HashSet<>();
	
	public void addRoute(RouteEntity route) {
		this.routes.add(route);
	}
	
	public void removeRoutes(Collection<RouteEntity> route) {
		route.forEach(routeToRemove -> {
			this.routes.removeIf(r -> r.equals(routeToRemove));
		});
	}

	@Override
	public String toString() {
		return "OriginEntity: ".concat(new Gson().toJson(this));
	}
	
}
