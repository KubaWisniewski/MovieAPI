package com.app.repository;

import com.app.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByTitle(String title);
    List<Movie> findAllByGenre(String genre);
    List<Movie> findAllByReleaseBetween(LocalDate release1, LocalDate release2);
}
