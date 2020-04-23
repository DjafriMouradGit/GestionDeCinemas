package com.gestionCinema.service;

import com.gestionCinema.entities.*;
import com.gestionCinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
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
        Stream.of("Alger", "Tipaza", "Blida").forEach(nameVille -> {
                    Ville ville = new Ville();
                    ville.setName(nameVille);
                    villeRepository.save(ville);
                }
        );
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(ville -> {
            Stream.of("FOUNOUN", "CHAHRAZAD").forEach(nameCinema -> {
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
        double[] durees = new double[]{1, 1.5, 2, 2.5, 3};
        List<Categorie> categories = categoryRepository.findAll();
        Stream.of("Game of thrones", "Seigneur des anneaux", "Spider man", "IRON man", "Cat women").forEach(titreFilm -> {
                    Film film = new Film();
                    film.setTitre(titreFilm);
                    film.setDuree(new Random().nextInt(durees.length));
                    film.setPhoto(titreFilm.replaceAll(" ", ""));
                    film.setCategorie(categories.get(new Random().nextInt(categories.size())));
                    filmRepository.save(film);
                }
        );
    }

    @Override
    public void initProjections() {
        double[] prices = new double[]{30, 50, 60, 70, 90, 100};
        salleRepository.findAll().forEach(salle -> {
            filmRepository.findAll().forEach(film -> {
                seanceRepository.findAll().forEach(seance -> {
                    Projection projection = new Projection();
                    projection.setDateProjection(new Date());
                    projection.setPrix(prices[new Random().nextInt(prices.length)]);
                    projection.setSalle(salle);
                    projection.setFilm(film);
                    projection.setSeance(seance);
                    projectionRepository.save(projection);
                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(projection -> {
            projection.getSalle().getPlaces().forEach(place -> {
                Ticket ticket=new Ticket();
                ticket.setPrix(projection.getPrix());
                ticket.setReserve(false);
                ticket.setPlace(place);
                ticket.setProjection(projection);
                ticketRepository.save(ticket);
            });
        });
    }
}
