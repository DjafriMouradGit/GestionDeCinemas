package com.gestionCinema.Controller;

import com.gestionCinema.entities.Film;
import com.gestionCinema.entities.Ticket;
import com.gestionCinema.repository.FilmRepository;
import com.gestionCinema.repository.TicketRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaController {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;

    //    Récuperer une image selon l'id, cette image se trouve dans un dossier système
    @GetMapping(path = "/images/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws IOException {
        Film film = filmRepository.findById(id).get();
        String photoName = film.getPhoto();
        File file = new File(System.getProperty("user.home") + "/cinema/images/" + photoName + ".jpg");
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    // Payer des tickets
    @PostMapping(path = "/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketFrom){
        System.out.println("ticketFrom.getNomClient() = " + ticketFrom.getNomClient());
        System.out.println("ticketFrom.getCodePayement() = " + ticketFrom.getCodePayement());
        List<Ticket> tickets=new ArrayList<>();
        ticketFrom.getTickets().forEach(idTicket ->{
            Ticket ticket=ticketRepository.findById(idTicket).get();
            ticket.setNomClient(ticketFrom.getNomClient());
            ticket.setCodePayement(ticketFrom.getCodePayement());
            ticket.setReserve(true);
            ticketRepository.save(ticket);
            tickets.add(ticket);
        });
        return tickets;
    }

    @Data
    static class TicketForm{
        private String nomClient;
        private int codePayement;
        private List<Long> tickets=new ArrayList<>();

        public TicketForm() {
        }
    }
}
