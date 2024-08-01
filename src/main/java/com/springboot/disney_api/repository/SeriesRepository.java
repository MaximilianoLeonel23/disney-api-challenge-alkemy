package com.springboot.disney_api.repository;

import com.springboot.disney_api.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    @Query("SELECT s FROM Series s WHERE " +
            "(:name IS NULL OR s.title LIKE %:name%) AND " +
            "(:genres IS NULL OR EXISTS (SELECT 1 FROM s.genres g WHERE g.id IN :genres))")
    List<Series> findAllWithFilters(@Param("name") String name,
                                    @Param("genres") List<Long> genres);
}
