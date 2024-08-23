// package com.cinemagic.cinemagic.movie;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/movies")
// public class MovieController {

//     @Autowired
//     private MovieService movieService;

//     @GetMapping
//     public List<Movie> getAllMovies() {
//         return movieService.getAllMovies();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
//         Optional<Movie> movie = movieService.getMovieById(id);
//         return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @PostMapping
//     public Movie createMovie(@RequestBody Movie movie) {
//         return movieService.createMovie(movie);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
//         try {
//             Movie updatedMovie = movieService.updateMovie(id, movieDetails);
//             return ResponseEntity.ok(updatedMovie);
//         } catch (RuntimeException e) {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
//         movieService.deleteMovie(id);
//         return ResponseEntity.noContent().build();
//     }
// }


package com.cinemagic.cinemagic.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody MovieDTO movieDTO) {
        Movie movie = movieService.createMovie(movieDTO);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        try {
            Movie updatedMovie = movieService.updateMovie(id, movieDTO);
            return ResponseEntity.ok(updatedMovie);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
