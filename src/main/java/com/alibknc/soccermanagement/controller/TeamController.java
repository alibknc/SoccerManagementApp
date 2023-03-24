package com.alibknc.soccermanagement.controller;

import com.alibknc.soccermanagement.model.request.CreateTeamRequest;
import com.alibknc.soccermanagement.model.request.UpdateTeamRequest;
import com.alibknc.soccermanagement.model.response.TeamDto;
import com.alibknc.soccermanagement.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable UUID id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody CreateTeamRequest team) {
        return ResponseEntity.ok(teamService.createTeam(team));
    }

    @PutMapping
    public ResponseEntity<TeamDto> updateTeam(@RequestBody UpdateTeamRequest team) {
        return ResponseEntity.ok(teamService.updateTeam(team));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTeam(@PathVariable UUID id) {
        teamService.deleteTeam(id);
        return ResponseEntity.ok().build();
    }

}
