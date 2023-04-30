package com.example.milashuk.kcres.controller;

import com.example.milashuk.kcres.domain.Workout;
import com.example.milashuk.kcres.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping
    public void add(@RequestBody Workout workout) {
        workoutService.saveWorkout(workout);
    }

    @GetMapping
    public List<Workout> findAll(Authentication authentication) {
        return workoutService.findWorkouts();
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        workoutService.deleteWorkout(id);
    }
}
