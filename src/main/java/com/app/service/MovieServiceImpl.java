package com.app.service;

import com.app.model.Movie;
import com.app.model.dto.MovieDto;
import com.app.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;
    private ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MovieDto addOrUpdateMovie(MovieDto movieDto) {
        if (movieDto == null) {
            throw new NullPointerException("MOVIE IS NULL");
        }
        Movie movie =  movieRepository.save(modelMapper.map(movieDto, Movie.class));
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public MovieDto deleteMovie(Long id) {
        Optional<Movie> movieOp = movieRepository.findById(id);
        if (!movieOp.isPresent()) {
            throw new NullPointerException("ID IS NULL");
        }
        Movie movie = movieOp.get();
        movieRepository.delete(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public MovieDto findMovieById(Long id) {
        if (id == null) {
            throw new NullPointerException("ID IS NULL");
        }

        Optional<Movie> movieOp = movieRepository.findById(id);
        if (!movieOp.isPresent()) {
            throw new NullPointerException("CANNOT FIND MOVIE WITH ID " + id);
        }

        return modelMapper.map(movieOp.get(), MovieDto.class);
    }

    @Override
    public List<MovieDto> findAllMovies() {
        return movieToMovieDtoConversion(movieRepository.findAll());
    }

    @Override
    public List<MovieDto> findAllMoviesByGenre(String genre) {
        if (genre == null) {
            throw new NullPointerException("GENRE IS NULL");
        }
        return movieToMovieDtoConversion(movieRepository.findAllByGenre(genre));
    }

    @Override
    public List<MovieDto> findAllMoviesByReleaseDateBetween(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) {
            throw new NullPointerException("DATES CANNOT BE NULL");
        }

        if (date1.isAfter(date2)) {
            throw new NullPointerException("DATES ARE NOT IN CORRECT ORDER");
        }

        return movieToMovieDtoConversion(movieRepository.findAllByReleaseBetween(date1, date2));
    }

    @Override
    public List<MovieDto> findAllMoviesByTitle(String title) {
        if (title == null) {
            throw new NullPointerException("TITLE IS NULL");
        }

        return movieToMovieDtoConversion(movieRepository.findAllByTitle(title));
    }

    private List<MovieDto> movieToMovieDtoConversion(List<Movie> movies) {
        return movies
                .stream()
                .map(m -> modelMapper.map(m, MovieDto.class))
                .collect(Collectors.toList());
    }
}
