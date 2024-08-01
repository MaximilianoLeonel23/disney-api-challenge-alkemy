package com.springboot.disney_api.repository;

import com.springboot.disney_api.model.Character;
import com.springboot.disney_api.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query("SELECT c FROM Character c WHERE " +
    "(:name IS NULL OR c.name LIKE %:name%) AND " +
    "(:age IS NULL OR c.age = :age) AND " +
    "(:weight IS NULL OR c.weight = :weight) AND " +
    "(:movies IS NULL OR EXISTS (SELECT 1 FROM c.movies m WHERE m.id IN :movies)) AND " +
    "(:series IS NULL OR EXISTS (SELECT 1 FROM c.series s WHERE s.id IN :series))")
    List<Character> findAllWithFilters(@Param("name") String name,
                                       @Param("age") Integer age,
                                       @Param("weight") Double weight,
                                       @Param("movies") List<Long> movies,
                                       @Param("series") List<Long> series
                                       );

}
