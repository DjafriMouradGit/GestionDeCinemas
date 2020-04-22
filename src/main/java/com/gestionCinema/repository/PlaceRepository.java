package com.gestionCinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionCinema.entities.Place;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlaceRepository extends JpaRepository<Place,Long> {
}
