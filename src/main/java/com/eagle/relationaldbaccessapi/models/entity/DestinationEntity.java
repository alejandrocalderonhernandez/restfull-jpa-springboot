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
import javax.persistence.ManyToMany;
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
	private AddressEntity address;

	@ManyToMany(mappedBy = "destinations", cascade = CascadeType.ALL)
	private Set<RouteEntity> routes = new HashSet<>();

	public void addRoute(RouteEntity route) {
		this.routes.add(route);
	}
	
	public void removeRoute(Collection<RouteEntity> route) {
		route.stream().forEach(routeToRemove -> {
			this.routes.removeIf(r -> r.equals(routeToRemove));
		});
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alternativeId == null) ? 0 : alternativeId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof DestinationEntity)) return false;
		DestinationEntity other = (DestinationEntity) obj;
		if (alternativeId == null) {
			if (other.alternativeId != null) return false;
		} else if (!alternativeId.equals(other.alternativeId)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "DestinationDTO: ".concat(new Gson().toJson(this));
	}
	
}
