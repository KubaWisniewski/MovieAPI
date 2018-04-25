package com.app.service;

import com.app.model.Movie;
import com.app.model.dto.MovieDto;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    MovieDto addOrUpdateMovie(MovieDto movieDto);
    MovieDto deleteMovie(Long id);
    MovieDto findMovieById(Long id);
    List<MovieDto> findAllMovies();
    List<MovieDto> findAllMoviesByGenre(String genre);
    List<MovieDto> findAllMoviesByReleaseDateBetween(LocalDate date1, LocalDate date2);
    List<MovieDto> findAllMoviesByTitle(String title);
}
