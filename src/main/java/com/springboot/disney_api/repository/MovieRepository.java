package com.springboot.disney_api.repository;

import com.springboot.disney_api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE " +
    "(:name IS NULL OR m.title LIKE %:name%) AND " +
    "(:genres IS NULL OR EXISTS (SELECT 1 FROM m.genres g WHERE g.id IN :genres))")
    List<Movie> findAllWithFilters(@Param("name") String name,
                                   @Param("genres") List<Long> genres);
}
