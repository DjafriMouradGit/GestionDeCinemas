package com.gestionCinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestionCinema.commun.Patterns;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
@ToString
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Pattern(regexp = Patterns.PATTERN_SCRIPT, message = Patterns.MSG_VERIFICATION_SCRIPT)
    private String nomClient;
    private double prix;
    @Column(unique = false,nullable = true)
    private int codePayement;
    private boolean reserve;
    @JoinColumn(name = "id_place", referencedColumnName = "id")
    @ManyToOne
    private Place place;
    @JoinColumn(name = "id_projection", referencedColumnName = "id")
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Projection  projection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getCodePayement() {
        return codePayement;
    }

    public void setCodePayement(int codePayement) {
        this.codePayement = codePayement;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Projection getProjection() {
        return projection;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ticket other = (Ticket) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}