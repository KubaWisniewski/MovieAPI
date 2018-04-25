package com.app;

import com.app.model.dto.MovieDto;
import com.app.service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MovieInitializer implements CommandLineRunner {

    private MovieService movieService;

    public MovieInitializer(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void run(String... args) throws Exception {
        movieService.addOrUpdateMovie(MovieDto.builder().title("MOVIE1").genre("GENRE1").release(LocalDate.of(2010,12,1)).build());
        movieService.addOrUpdateMovie(MovieDto.builder().title("MOVIE2").genre("GENRE2").release(LocalDate.of(2013,10,1)).build());
        movieService.addOrUpdateMovie(MovieDto.builder().title("MOVIE3").genre("GENRE3").release(LocalDate.of(2011,9,1)).build());
        movieService.addOrUpdateMovie(MovieDto.builder().title("MOVIE4").genre("GENRE4").release(LocalDate.of(2014,7,1)).build());
        movieService.addOrUpdateMovie(MovieDto.builder().title("MOVIE5").genre("GENRE5").release(LocalDate.of(2015,8,1)).build());
    }
}
