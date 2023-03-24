package com.alibknc.soccermanagement.service;

import com.alibknc.soccermanagement.base.BaseUnitTest;
import com.alibknc.soccermanagement.model.entity.Player;
import com.alibknc.soccermanagement.model.entity.Team;
import com.alibknc.soccermanagement.model.request.CreatePlayerRequest;
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
    public void getAllPlayers() {
        List<Player> list = entityFactory.playerList();
        when(playerRepository.findAll()).thenReturn(list);

        List<PlayerDto> result = playerService.getAllPlayers();

        assertFalse(result.isEmpty());
        assertEquals(result.size(), list.size());
    }

    @Test
    public void getPlayersByTeamId() {
        List<Player> list = entityFactory.playerList();
        when(playerRepository.getByTeamId(any())).thenReturn(list);

        UUID id = objectFactory.randomUUID();
        List<PlayerDto> result = playerService.getPlayersByTeamId(id);

        assertFalse(result.isEmpty());
        assertEquals(result.size(), list.size());
    }

    @Test
    public void createPlayerSuccess() {
        Player player = entityFactory.player();
        Team team = entityFactory.team();
        List<Player> tempList = entityFactory.generatePlayerList(1);

        when(playerRepository.getPlayersOfTeamByIdAndStatus(any(), any())).thenReturn(tempList);
        when(playerRepository.getPlayersOfTeamByIdAndPosition(any(), any())).thenReturn(tempList);
        when(playerRepository.getByTeamId(any())).thenReturn(tempList);
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(playerRepository.save(any())).thenReturn(player);

        CreatePlayerRequest request = objectFactory.playerCreateRequest();
        PlayerDto result = playerService.createPlayer(request);

        assertNotNull(result);
        assertEquals(request.getName(), result.getName());
    }

    @Test
    public void createPlayerFailedForStatus() {
        Player player = entityFactory.player();
        Team team = entityFactory.team();
        List<Player> tempList = entityFactory.generatePlayerList(6);

        when(playerRepository.getPlayersOfTeamByIdAndStatus(any(), any())).thenReturn(tempList);
        when(playerRepository.getPlayersOfTeamByIdAndPosition(any(), any())).thenReturn(tempList);
        when(playerRepository.getByTeamId(any())).thenReturn(tempList);
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(playerRepository.save(any())).thenReturn(player);

        try {
            CreatePlayerRequest request = objectFactory.playerCreateRequest();
            PlayerDto result = playerService.createPlayer(request);
            assertNull(result);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Maximum Foreign Player Limit Reached");
        }
    }

    @Test
    public void createPlayerFailedForPosition() {
        Player player = entityFactory.player();
        Team team = entityFactory.team();
        List<Player> tempList = entityFactory.generatePlayerList(2);

        when(playerRepository.getPlayersOfTeamByIdAndStatus(any(), any())).thenReturn(tempList);
        when(playerRepository.getPlayersOfTeamByIdAndPosition(any(), any())).thenReturn(tempList);
        when(playerRepository.getByTeamId(any())).thenReturn(tempList);
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(playerRepository.save(any())).thenReturn(player);

        try {
            CreatePlayerRequest request = objectFactory.playerCreateRequest();
            PlayerDto result = playerService.createPlayer(request);
            assertNull(result);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Maximum GoalKeeper Player Limit Reached");
        }
    }

    @Test
    public void createPlayerFailedForTotalLimit() {
        Player player = entityFactory.player();
        Team team = entityFactory.team();
        List<Player> tempList = entityFactory.generatePlayerList(18);

        when(playerRepository.getPlayersOfTeamByIdAndStatus(any(), any())).thenReturn(tempList);
        when(playerRepository.getPlayersOfTeamByIdAndPosition(any(), any())).thenReturn(tempList);
        when(playerRepository.getByTeamId(any())).thenReturn(tempList);
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(playerRepository.save(any())).thenReturn(player);

        try {
            CreatePlayerRequest request = objectFactory.playerCreateRequest();
            PlayerDto result = playerService.createPlayer(request);
            assertNull(result);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Maximum Player Limit Reached");
        }
    }

    @Test
    public void deletePlayersOfTeam() {
        UUID id = objectFactory.randomUUID();
        playerService.deletePlayersOfTeam(id);
    }

}