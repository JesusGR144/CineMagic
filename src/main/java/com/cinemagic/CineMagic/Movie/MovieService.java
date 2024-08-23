// package com.cinemagic.cinemagic.movie;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.cinemagic.cinemagic.exceptions.ApiRequestException;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class MovieService {

//     @Autowired
//     private MovieRepository movieRepository;

//     public List<Movie> getAllMovies() {
//         return movieRepository.findAll();
//     }

//     public Optional<Movie> getMovieById(Long id) {
//         return movieRepository.findById(id);
//     }

//     public Movie createMovie(Movie movie) {
//         return movieRepository.save(movie);
//     }

//     public Movie updateMovie(Long id, Movie movieDetails) {
//         return movieRepository.findById(id).map(movie -> {
//             movie.setTitle(movieDetails.getTitle());
//             // movie.setDescription(movieDetails.getDescription());
//             movie.setReleaseDate(movieDetails.getReleaseDate());
//             // movie.setDuration(movieDetails.getDuration());
//             movie.setGenre(movieDetails.getGenre());
//             return movieRepository.save(movie);
//         }).orElseThrow(() -> new ApiRequestException("Movie not found with id " + id));
//     }

//     public void deleteMovie(Long id) {
//         movieRepository.deleteById(id);
//     }
// }


package com.cinemagic.cinemagic.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemagic.cinemagic.exceptions.ApiRequestException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie createMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setGenre(movieDTO.getGenre());
        // Puedes agregar más campos si es necesario
        
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(movieDTO.getTitle());
            movie.setReleaseDate(movieDTO.getReleaseDate());
            movie.setGenre(movieDTO.getGenre());
            // Puedes agregar más campos si es necesario
            
            return movieRepository.save(movie);
        }).orElseThrow(() -> new ApiRequestException("Movie not found with id " + id));
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
