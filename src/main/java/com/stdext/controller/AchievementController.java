package com.stdext.controller;

import com.stdext.model.Achievement;
import com.stdext.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/achievements")
@CrossOrigin(origins = "*")
public class AchievementController {

    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Achievement>> getStudentAchievements(@PathVariable String studentId) {
        return ResponseEntity.ok(achievementService.findByStudentId(studentId));
    }

    @PostMapping("/add")
    public ResponseEntity<Achievement> addRecord(@RequestBody Achievement achievement) {
        return ResponseEntity.status(201).body(achievementService.save(achievement));
    }

    @PatchMapping("/{id}/verify")
    public ResponseEntity<Achievement> verifyRecord(@PathVariable String id) {
        return ResponseEntity.ok(achievementService.verify(id));
    }

    // ✅ TEMP TEST API
    @GetMapping("/hello")
    public String hello() {
        return "Hello Working";
    }
}