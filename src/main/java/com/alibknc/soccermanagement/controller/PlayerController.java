package com.alibknc.soccermanagement.controller;

import com.alibknc.soccermanagement.model.request.CreatePlayerRequest;
import com.alibknc.soccermanagement.model.response.PlayerDto;
import com.alibknc.soccermanagement.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<List<PlayerDto>> getPlayersByTeamId(@PathVariable UUID teamId) {
        return ResponseEntity.ok(playerService.getPlayersByTeamId(teamId));
    }

    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody CreatePlayerRequest player) {
        return ResponseEntity.ok(playerService.createPlayer(player));
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Object> deletePlayersOfTeam(@PathVariable UUID teamId) {
        playerService.deletePlayersOfTeam(teamId);
        return ResponseEntity.ok().build();
    }

}
