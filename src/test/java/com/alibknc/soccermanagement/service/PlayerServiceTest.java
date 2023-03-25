package com.alibknc.soccermanagement.service;

import com.alibknc.soccermanagement.base.BaseUnitTest;
import com.alibknc.soccermanagement.model.entity.Player;
import com.alibknc.soccermanagement.model.entity.Team;
import com.alibknc.soccermanagement.model.request.CreatePlayerRequest;
import com.alibknc.soccermanagement.model.request.UpdatePlayerRequest;
import com.alibknc.soccermanagement.model.response.PlayerDto;
import com.alibknc.soccermanagement.repository.PlayerRepository;
import com.alibknc.soccermanagement.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerServiceTest extends BaseUnitTest {

    private PlayerService playerService;
    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;

    @BeforeEach
    public void setUp() {
        playerRepository = mock(PlayerRepository.class);
        teamRepository = mock(TeamRepository.class);
        playerService = new PlayerService(playerRepository, teamRepository);
    }

    @Test
    void getAllPlayers() {
        List<Player> list = entityFactory.playerList();
        when(playerRepository.findAll()).thenReturn(list);

        List<PlayerDto> result = playerService.getAllPlayers();

        assertFalse(result.isEmpty());
        assertEquals(result.size(), list.size());
    }

    @Test
    void getPlayerByIdSuccess() {
        Player player = entityFactory.player();

        when(playerRepository.findById(any())).thenReturn(Optional.of(player));

        UUID id = objectFactory.randomUUID();
        PlayerDto result = playerService.getPlayerById(id);

        assertNotNull(result.getName(), player.getName());
    }

    @Test
    void getPlayerByIdFailed() {
        when(playerRepository.findById(any())).thenReturn(Optional.empty());

        try {
            UUID id = objectFactory.randomUUID();
            PlayerDto result = playerService.getPlayerById(id);
            assertNull(result);
        } catch (Exception e) {
            assertEquals("Player Not Found", e.getMessage());
        }
    }

    @Test
    void getPlayersByTeamId() {
        List<Player> list = entityFactory.playerList();
        when(playerRepository.getPlayersByTeamId(any())).thenReturn(list);

        UUID id = objectFactory.randomUUID();
        List<PlayerDto> result = playerService.getPlayersByTeamId(id);

        assertFalse(result.isEmpty());
        assertEquals(result.size(), list.size());
    }

    @Test
    void createPlayerSuccess() {
        Player player = entityFactory.player();
        Team team = entityFactory.team();
        List<Player> tempList = entityFactory.generatePlayerList(1);

        when(playerRepository.getPlayerCountByTeamIdAndStatus(any(), any())).thenReturn(tempList.size());
        when(playerRepository.getPlayerCountByTeamIdAndPosition(any(), any())).thenReturn(tempList.size());
        when(playerRepository.getPlayerCountByTeamId(any())).thenReturn(tempList.size());
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(playerRepository.save(any())).thenReturn(player);

        CreatePlayerRequest request = objectFactory.playerCreateRequest();
        PlayerDto result = playerService.createPlayer(request);

        assertNotNull(result);
        assertEquals(request.getName(), result.getName());
    }

    @Test
    void createPlayerFailedForStatus() {
        Player player = entityFactory.player();
        Team team = entityFactory.team();
        List<Player> tempList = entityFactory.generatePlayerList(6);

        when(playerRepository.getPlayerCountByTeamIdAndStatus(any(), any())).thenReturn(tempList.size());
        when(playerRepository.getPlayerCountByTeamIdAndPosition(any(), any())).thenReturn(tempList.size());
        when(playerRepository.getPlayerCountByTeamId(any())).thenReturn(tempList.size());
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(playerRepository.save(any())).thenReturn(player);

        try {
            CreatePlayerRequest request = objectFactory.playerCreateRequest();
            PlayerDto result = playerService.createPlayer(request);
            assertNull(result);
        } catch (Exception e) {
            assertEquals("Maximum Foreign Player Limit Reached", e.getMessage());
        }
    }

    @Test
    void createPlayerFailedForPosition() {
        Player player = entityFactory.player();
        Team team = entityFactory.team();
        List<Player> tempList = entityFactory.generatePlayerList(2);

        when(playerRepository.getPlayerCountByTeamIdAndStatus(any(), any())).thenReturn(tempList.size());
        when(playerRepository.getPlayerCountByTeamIdAndPosition(any(), any())).thenReturn(tempList.size());
        when(playerRepository.getPlayerCountByTeamId(any())).thenReturn(tempList.size());
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(playerRepository.save(any())).thenReturn(player);

        try {
            CreatePlayerRequest request = objectFactory.playerCreateRequest();
            PlayerDto result = playerService.createPlayer(request);
            assertNull(result);
        } catch (Exception e) {
            assertEquals("Maximum GoalKeeper Player Limit Reached", e.getMessage());
        }
    }

    @Test
    void createPlayerFailedForTotalLimit() {
        Player player = entityFactory.player();
        Team team = entityFactory.team();
        List<Player> tempList = entityFactory.generatePlayerList(18);

        when(playerRepository.getPlayerCountByTeamIdAndStatus(any(), any())).thenReturn(tempList.size());
        when(playerRepository.getPlayerCountByTeamIdAndPosition(any(), any())).thenReturn(tempList.size());
        when(playerRepository.getPlayerCountByTeamId(any())).thenReturn(tempList.size());
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(playerRepository.save(any())).thenReturn(player);

        try {
            CreatePlayerRequest request = objectFactory.playerCreateRequest();
            PlayerDto result = playerService.createPlayer(request);
            assertNull(result);
        } catch (Exception e) {
            assertEquals("Maximum Player Limit Reached", e.getMessage());
        }
    }

    @Test
    void updatePlayerSuccess() {
        Player player = entityFactory.playerForUpdate();
        Team team = entityFactory.team();
        List<Player> tempList = entityFactory.generatePlayerList(1);

        when(playerRepository.getPlayerCountByTeamIdAndStatus(any(), any())).thenReturn(tempList.size());
        when(playerRepository.getPlayerCountByTeamIdAndPosition(any(), any())).thenReturn(tempList.size());
        when(playerRepository.findById(any())).thenReturn(Optional.of(player));
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(playerRepository.save(any())).thenReturn(player);

        UpdatePlayerRequest request = objectFactory.playerUpdateRequest();
        PlayerDto result = playerService.updatePlayer(request);

        assertNotNull(result);
        assertEquals(request.getName(), result.getName());
    }

    @Test
    void updatePlayerFailedForPosition() {
        Player player = entityFactory.playerForUpdate();
        Team team = entityFactory.team();
        List<Player> tempList = entityFactory.generatePlayerList(2);

        when(playerRepository.getPlayerCountByTeamIdAndStatus(any(), any())).thenReturn(tempList.size());
        when(playerRepository.getPlayerCountByTeamIdAndPosition(any(), any())).thenReturn(tempList.size());
        when(playerRepository.findById(any())).thenReturn(Optional.of(player));
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(playerRepository.save(any())).thenReturn(player);

        try {
            UpdatePlayerRequest request = objectFactory.playerUpdateRequest();
            PlayerDto result = playerService.updatePlayer(request);
            assertNull(result);
        } catch (Exception e) {
            assertEquals("Maximum GoalKeeper Player Limit Reached", e.getMessage());
        }
    }

    @Test
    void updatePlayerFailedForStatus() {
        Player player = entityFactory.playerForUpdate();
        Team team = entityFactory.team();
        List<Player> tempList = entityFactory.generatePlayerList(6);

        when(playerRepository.getPlayerCountByTeamIdAndStatus(any(), any())).thenReturn(tempList.size());
        when(playerRepository.getPlayerCountByTeamIdAndPosition(any(), any())).thenReturn(tempList.size());
        when(playerRepository.findById(any())).thenReturn(Optional.of(player));
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(playerRepository.save(any())).thenReturn(player);

        try {
            UpdatePlayerRequest request = objectFactory.playerUpdateRequest();
            PlayerDto result = playerService.updatePlayer(request);
            assertNull(result);
        } catch (Exception e) {
            assertEquals("Maximum Foreign Player Limit Reached", e.getMessage());
        }
    }

    @Test
    void updatePlayerFailedForNotFound() {
        Player player = entityFactory.playerForUpdate();
        Team team = entityFactory.team();
        List<Player> tempList = entityFactory.generatePlayerList(1);

        when(playerRepository.getPlayerCountByTeamIdAndStatus(any(), any())).thenReturn(tempList.size());
        when(playerRepository.getPlayerCountByTeamIdAndPosition(any(), any())).thenReturn(tempList.size());
        when(playerRepository.findById(any())).thenReturn(Optional.empty());
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(playerRepository.save(any())).thenReturn(player);

        try {
            UpdatePlayerRequest request = objectFactory.playerUpdateRequest();
            PlayerDto result = playerService.updatePlayer(request);
            assertNull(result);
        } catch (Exception e) {
            assertEquals("Player Not Available", e.getMessage());
        }
    }

    @Test
    void deletePlayerSuccess() {
        Player player = entityFactory.player();

        when(playerRepository.findById(any())).thenReturn(Optional.of(player));

        UUID id = objectFactory.randomUUID();
        playerService.deletePlayer(id);

        assertNotNull(player.getId());
    }

    @Test
    void deletePlayerFailed() {
        when(playerRepository.findById(any())).thenReturn(Optional.empty());

        try {
            UUID id = objectFactory.randomUUID();
            playerService.deletePlayer(id);
        } catch (Exception e) {
            assertEquals("Player Not Found", e.getMessage());
        }
    }

    @Test
    void deletePlayersByTeamId() {
        Team team = entityFactory.team();

        when(teamRepository.findById(any())).thenReturn(Optional.of(team));

        UUID id = objectFactory.randomUUID();
        playerService.deletePlayersByTeamId(id);

        assertNotNull(team.getId());
    }

    @Test
    void deletePlayersByTeamIdFailed() {
        when(playerRepository.findById(any())).thenReturn(Optional.empty());

        try {
            UUID id = objectFactory.randomUUID();
            playerService.deletePlayersByTeamId(id);
        } catch (Exception e) {
            assertEquals("Team Not Found", e.getMessage());
        }
    }

}