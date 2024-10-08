// package com.cinemagic.cinemagic.schedule;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.cinemagic.cinemagic.exceptions.ApiRequestException;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class SchedubleService {

//     @Autowired
//     private SchedubleRepository schedubleRepository;

//     public List<Schedule> getAllSchedubles() {
//         return schedubleRepository.findAll();
//     }

//     public Optional<Schedule> getSchedubleById(Long id) {
//         return schedubleRepository.findById(id);
//     }

//     public Schedule createScheduble(Schedule scheduble) {
//         return schedubleRepository.save(scheduble);
//     }

//     public Schedule updateScheduble(Long id, Schedule schedubleDetails) {
//         return schedubleRepository.findById(id).map(scheduble -> {
//             scheduble.setMovie(schedubleDetails.getMovie());
//             scheduble.setDateTime(schedubleDetails.getDateTime());
//             scheduble.setCinemaRoom(schedubleDetails.getCinemaRoom());
//             return schedubleRepository.save(scheduble);
//         }).orElseThrow(() -> new ApiRequestException("Scheduble not found with id " + id));
//     }

//     public void deleteScheduble(Long id) {
//         schedubleRepository.deleteById(id);
//     }
// }


package com.cinemagic.cinemagic.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemagic.cinemagic.exceptions.ApiRequestException;
import com.cinemagic.cinemagic.movie.Movie;
import com.cinemagic.cinemagic.movie.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SchedubleService {

    @Autowired
    private SchedubleRepository schedubleRepository;

    @Autowired
    private MovieRepository movieRepository; // Asegúrate de tener un repositorio para Movie

    public List<Schedule> getAllSchedubles() {
        return schedubleRepository.findAll();
    }

    public Optional<Schedule> getSchedubleById(Long id) {
        return schedubleRepository.findById(id);
    }

    public Schedule createScheduble(ScheduleDTO scheduleDTO) {
        Schedule scheduble = new Schedule();
        
        // Asignar el dateTime como String directamente
        scheduble.setDateTime(scheduleDTO.getDateTime());
        scheduble.setCinemaRoom(scheduleDTO.getCinemaRoom());

        // Obtener la instancia de Movie desde el repositorio
        Movie movie = movieRepository.findById(scheduleDTO.getMovieId())
                .orElseThrow(() -> new ApiRequestException("Movie not found with id " + scheduleDTO.getMovieId()));
        scheduble.setMovie(movie);

        return schedubleRepository.save(scheduble);
    }

    public Schedule updateScheduble(Long id, ScheduleDTO scheduleDTO) {
        return schedubleRepository.findById(id).map(scheduble -> {
            // Asignar el dateTime como String directamente
            scheduble.setDateTime(scheduleDTO.getDateTime());
            scheduble.setCinemaRoom(scheduleDTO.getCinemaRoom());

            // Obtener la instancia de Movie desde el repositorio
            Movie movie = movieRepository.findById(scheduleDTO.getMovieId())
                    .orElseThrow(() -> new ApiRequestException("Movie not found with id " + scheduleDTO.getMovieId()));
            scheduble.setMovie(movie);

            return schedubleRepository.save(scheduble);
        }).orElseThrow(() -> new ApiRequestException("Scheduble not found with id " + id));
    }

    public void deleteScheduble(Long id) {
        schedubleRepository.deleteById(id);
    }
}
