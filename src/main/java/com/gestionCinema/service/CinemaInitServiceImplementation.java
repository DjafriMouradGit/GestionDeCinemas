package com.gestionCinema.service;

import com.gestionCinema.entities.*;
import com.gestionCinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

@Service
public class CinemaInitServiceImplementation implements ICinemaInitService {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ProjectionRepository projectionRepository;

    @Override
    public void initVilles() {
        Stream.of("Alger", "Tipaza", "Blida", "Oran").forEach(nameVille -> {
                    Ville ville = new Ville();
                    ville.setName(nameVille);
                    villeRepository.save(ville);
                }
        );
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(ville -> {
            Stream.of("MegaBama", "IMAX", "FOUNOUN", "CHAHRAZAD").forEach(nameCinema -> {
                        Cinema cinema = new Cinema();
                        cinema.setName(nameCinema);
                        cinema.setVille(ville);
                        cinema.setNombreSalles(20 + (int) (Math.random() * 20));
                        cinemaRepository.save(cinema);
                    }
            );
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i = 0; i < cinema.getNombreSalles(); i++) {
                Salle salle = new Salle();
                salle.setName("Salle" + (i + 1));
                salle.setCinema(cinema);
                salle.setNombrePlaces(20 + (int) (Math.random() * 20));
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i = 0; i < salle.getNombrePlaces(); i++) {
                Place place = new Place();
                place.setNumero(i + 1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(s -> {
                    Seance seance = new Seance();
                    try {
                        seance.setHeureDebut(dateFormat.parse(s));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    seanceRepository.save(seance);
                }
        );
    }

    @Override
    public void initCategories() {
        Stream.of("Histoire", "Actions", "Fiction", "Drama").forEach(category -> {
                    Categorie categorie = new Categorie();
                    categorie.setName(category);
                    categoryRepository.save(categorie);
                }
        );
    }

    @Override
    public void initFilms() {
        Stream.of("Game of thrones", "Seigneur des anneaux", "Spider man", "IRON man", "Cat women").forEach(film -> {
                    Film film1 = new Film();
                    film1.setTitre(film);
                    filmRepository.save(film1);
                }
        );
    }

    @Override
    public void initProjections() {

    }

    @Override
    public void initTickets() {

    }
}
