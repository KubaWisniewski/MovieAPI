package com.app;

import com.app.model.Movie;
import com.app.model.dto.MovieDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class SpringbootMoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMoviesApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();

		PropertyMap<Movie, MovieDto> fromMovieToMovieDto = new PropertyMap<Movie, MovieDto>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setTitle(source.getTitle());
				map().setGenre(source.getGenre());
				map().setRelease(source.getRelease());
			}
		};

		PropertyMap<MovieDto, Movie> fromMovieDtoToMovie = new PropertyMap<MovieDto, Movie>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setTitle(source.getTitle());
				map().setGenre(source.getGenre());
				map().setRelease(source.getRelease());
			}
		};

		mapper.addMappings(fromMovieToMovieDto);
		mapper.addMappings(fromMovieDtoToMovie);
		return mapper;
	}
}
