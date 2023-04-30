package com.example.milashuk.kcres.repo;

import com.example.milashuk.kcres.domain.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query("select w from Workout  w where w.user = ?#{authentication.name}")
    List<Workout> findAllByUser();
}
