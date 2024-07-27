package com.cinemagic.cinemagic.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchedubleService {

    @Autowired
    private SchedubleRepository schedubleRepository;

    public List<Scheduble> getAllSchedubles() {
        return schedubleRepository.findAll();
    }

    public Optional<Scheduble> getSchedubleById(Long id) {
        return schedubleRepository.findById(id);
    }

    public Scheduble createScheduble(Scheduble scheduble) {
        return schedubleRepository.save(scheduble);
    }

    public Scheduble updateScheduble(Long id, Scheduble schedubleDetails) {
        return schedubleRepository.findById(id).map(scheduble -> {
            scheduble.setMovie(schedubleDetails.getMovie());
            scheduble.setDateTime(schedubleDetails.getDateTime());
            scheduble.setCinemaRoom(schedubleDetails.getCinemaRoom());
            return schedubleRepository.save(scheduble);
        }).orElseThrow(() -> new RuntimeException("Scheduble not found with id " + id));
    }

    public void deleteScheduble(Long id) {
        schedubleRepository.deleteById(id);
    }
}
