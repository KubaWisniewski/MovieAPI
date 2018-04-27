package com.app.controller;

import com.app.model.Movie;
import com.app.model.dto.MovieDto;
import com.app.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieRestController {

    private MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.findAllMovies();
    }

    @GetMapping("id/{id}")
    public MovieDto getMovieById(@PathVariable Long id) {
        return movieService.findMovieById(id);
    }

    @GetMapping("genre/{genre}")
    public List<MovieDto> getAllMoviesByGenre(@PathVariable String genre) {
        return movieService.findAllMoviesByGenre(genre);
    }

    @GetMapping("release/from/{release1}/to/{release2}")
    public List<MovieDto> getAllMoviesByReleaseDateBetween(@PathVariable String release1, @PathVariable String release2) {
        try {
            LocalDate releaseDate1 = LocalDate.parse(release1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate releaseDate2 = LocalDate.parse(release2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return movieService.findAllMoviesByReleaseDateBetween(releaseDate1, releaseDate2);
        } catch (Exception e) {
            throw new IllegalArgumentException("DATES FORMAT IS NOT CORRECT");
        }
    }
    @GetMapping("title/{title}")
    public List<MovieDto> getAllMoviesByTitle(@PathVariable String title){
        return movieService.findAllMoviesByTitle(title);
    }
    @PostMapping("/add")
    public MovieDto addMovie(@RequestBody MovieDto movieDto) {
        return movieService.addOrUpdateMovie(movieDto);
    }

    @DeleteMapping("/delete")
    public MovieDto deleteMovie(@RequestBody Long id) {
        return movieService.deleteMovie(id);
    }
    @PutMapping("/update")
    public MovieDto updateMovie(@RequestBody MovieDto movieDto){
        return movieService.addOrUpdateMovie(movieDto);
    }
}
