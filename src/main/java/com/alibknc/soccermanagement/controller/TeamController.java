package com.alibknc.soccermanagement.controller;

import com.alibknc.soccermanagement.model.request.CreateTeamRequest;
import com.alibknc.soccermanagement.model.request.UpdateTeamRequest;
import com.alibknc.soccermanagement.model.response.PlayerDto;
import com.alibknc.soccermanagement.model.response.TeamDto;
import com.alibknc.soccermanagement.service.PlayerService;
import com.alibknc.soccermanagement.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final PlayerService playerService;

    public TeamController(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable UUID id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @GetMapping("/{id}/players")
    public ResponseEntity<List<PlayerDto>> getPlayersByTeamId(@PathVariable UUID id) {
        return ResponseEntity.ok(playerService.getPlayersByTeamId(id));
    }

    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody CreateTeamRequest request) {
        return ResponseEntity.ok(teamService.createTeam(request));
    }

    @PutMapping
    public ResponseEntity<TeamDto> updateTeam(@RequestBody UpdateTeamRequest request) {
        return ResponseEntity.ok(teamService.updateTeam(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTeam(@PathVariable UUID id) {
        teamService.deleteTeam(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/players")
    public ResponseEntity<Object> deletePlayersOfTeam(@PathVariable UUID id) {
        playerService.deletePlayersTeamId(id);
        return ResponseEntity.ok().build();
    }

}
