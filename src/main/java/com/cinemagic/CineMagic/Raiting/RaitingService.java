// package com.cinemagic.cinemagic.raiting;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.cinemagic.cinemagic.exceptions.ApiRequestException;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class RaitingService {

//     @Autowired
//     private RaitingRepository raitingRepository;

//     public List<Raiting> getAllRaitings() {
//         return raitingRepository.findAll();
//     }

//     public Optional<Raiting> getRaitingById(Long id) {
//         return raitingRepository.findById(id);
//     }

//     public Raiting createRaiting(Raiting raiting) {
//         return raitingRepository.save(raiting);
//     }

//     public Raiting updateRaiting(Long id, Raiting raitingDetails) {
//         return raitingRepository.findById(id).map(raiting -> {
//             raiting.setUser(raitingDetails.getUser());
//             raiting.setMovie(raitingDetails.getMovie());
//             raiting.setScore(raitingDetails.getScore());
//             raiting.setComment(raitingDetails.getComment());
//             return raitingRepository.save(raiting);
//         }).orElseThrow(() -> new ApiRequestException("Raiting not found with id " + id));
//     }

//     public void deleteRaiting(Long id) {
//         raitingRepository.deleteById(id);
//     }
// }

package com.cinemagic.cinemagic.raiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemagic.cinemagic.exceptions.ApiRequestException;
import com.cinemagic.cinemagic.movie.Movie;
import com.cinemagic.cinemagic.movie.MovieRepository;
import com.cinemagic.cinemagic.user.UserEntity;
import com.cinemagic.cinemagic.user.UserRepository; // Asegúrate de tener el repositorio UserRepository

import java.util.List;
import java.util.Optional;

@Service
public class RaitingService {

    @Autowired
    private RaitingRepository raitingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository; 

    public List<Raiting> getAllRaitings() {
        return raitingRepository.findAll();
    }

    public Optional<Raiting> getRaitingById(Long id) {
        return raitingRepository.findById(id);
    }

    public Raiting createRaiting(RatingDTO ratingDTO) {
        Raiting raiting = new Raiting();
        
        raiting.setScore(ratingDTO.getScore());
        raiting.setComment(ratingDTO.getComment());

        Movie movie = movieRepository.findById(ratingDTO.getMovieId())
                .orElseThrow(() -> new ApiRequestException("Movie not found with id " + ratingDTO.getMovieId()));
        raiting.setMovie(movie);

        UserEntity user = userRepository.findById(ratingDTO.getUserId())
                .orElseThrow(() -> new ApiRequestException("UserEntity not found with id " + ratingDTO.getUserId()));
        raiting.setUser(user);

        return raitingRepository.save(raiting);
    }

    public Raiting updateRaiting(Long id, RatingDTO ratingDTO) {
        return raitingRepository.findById(id).map(raiting -> {
            raiting.setScore(ratingDTO.getScore());
            raiting.setComment(ratingDTO.getComment());

            Movie movie = movieRepository.findById(ratingDTO.getMovieId())
                    .orElseThrow(() -> new ApiRequestException("Movie not found with id " + ratingDTO.getMovieId()));
            raiting.setMovie(movie);

            UserEntity user = userRepository.findById(ratingDTO.getUserId())
                    .orElseThrow(() -> new ApiRequestException("UserEntity not found with id " + ratingDTO.getUserId()));
            raiting.setUser(user);

            return raitingRepository.save(raiting);
        }).orElseThrow(() -> new ApiRequestException("Raiting not found with id " + id));
    }

    public void deleteRaiting(Long id) {
        raitingRepository.deleteById(id);
    }
}
