package com.cinemagic.cinemagic.raiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaitingService {

    @Autowired
    private RaitingRepository raitingRepository;

    public List<Raiting> getAllRaitings() {
        return raitingRepository.findAll();
    }

    public Optional<Raiting> getRaitingById(Long id) {
        return raitingRepository.findById(id);
    }

    public Raiting createRaiting(Raiting raiting) {
        return raitingRepository.save(raiting);
    }

    public Raiting updateRaiting(Long id, Raiting raitingDetails) {
        return raitingRepository.findById(id).map(raiting -> {
            raiting.setUser(raitingDetails.getUser());
            raiting.setMovie(raitingDetails.getMovie());
            raiting.setScore(raitingDetails.getScore());
            raiting.setComment(raitingDetails.getComment());
            return raitingRepository.save(raiting);
        }).orElseThrow(() -> new RuntimeException("Raiting not found with id " + id));
    }

    public void deleteRaiting(Long id) {
        raitingRepository.deleteById(id);
    }
}
