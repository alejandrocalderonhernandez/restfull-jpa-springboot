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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name = "ROUTE")
public class RouteEntity implements Serializable{
	
	private static final long serialVersionUID = -8709784570414246618L;
	
	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_alternative", length = 30, nullable = true)
	private String alternativeId;
	@Column(name = "name_route", length = 30, nullable = false)
	private String name;
	@Column(name = "direct_travel", nullable = false)
	private Boolean directTravel;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_origin")
	private OriginEntity origin;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ROUTE_DESTINATION", 
            joinColumns = { @JoinColumn(name = "id_route") }, 
            inverseJoinColumns = { @JoinColumn(name = "id_destiny") }
        )
	private Set<DestinationEntity> destinations;
	
	public RouteEntity() {
		this.destinations = new HashSet<>();
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
	
	public Boolean isDirectTravel() {
		return directTravel;
	}
	
	public void setDirectTravel(Boolean directTravel) {
		this.directTravel = directTravel;
	}
	
	public OriginEntity getOrigin() {
		return origin;
	}
	
	public void setOrigin(OriginEntity origin) {
		this.origin = origin;
	}
	
	public Set<DestinationEntity> getDestinations() {
		return destinations;
	}
	
	public void setDestinations(Set<DestinationEntity> destinations) {
		this.destinations = destinations;
	}
	
	public void addDestination(DestinationEntity destination) {
		this.destinations.add(destination);
	}
	
	public void removeDestination(Collection<DestinationEntity> destination) {
		destination.stream().forEach(destinationToRemove -> {
			this.destinations.removeIf(d -> d.equals(destinationToRemove));
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
		if (!(obj instanceof RouteEntity)) return false;
		RouteEntity other = (RouteEntity) obj;
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
		return "DestinationEntity: ".concat(new Gson().toJson(this));
	}
	
}
