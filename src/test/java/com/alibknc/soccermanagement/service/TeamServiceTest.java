package com.alibknc.soccermanagement.service;

import com.alibknc.soccermanagement.base.BaseUnitTest;
import com.alibknc.soccermanagement.model.entity.Team;
import com.alibknc.soccermanagement.model.request.CreateTeamRequest;
import com.alibknc.soccermanagement.model.request.UpdateTeamRequest;
import com.alibknc.soccermanagement.model.response.TeamDto;
import com.alibknc.soccermanagement.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamServiceTest extends BaseUnitTest {

    private TeamService teamService;
    private TeamRepository teamRepository;

    @BeforeEach
    public void setUp() {
        teamRepository = mock(TeamRepository.class);
        teamService = new TeamService(teamRepository);
    }

    @Test
    void getAllTeams() {
        List<Team> list = entityFactory.teamList();
        when(teamRepository.findAll()).thenReturn(list);

        List<TeamDto> result = teamService.getAllTeams();

        assertFalse(result.isEmpty());
        assertEquals(result.size(), list.size());
    }

    @Test
    void getTeamByIdSuccess() {
        Team team = entityFactory.team();
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));

        UUID id = objectFactory.randomUUID();
        TeamDto result = teamService.getTeamById(id);

        assertNotNull(result);
        assertEquals(result.getName(), team.getName());
    }

    @Test
    void getTeamByIdFailed() {
        when(teamRepository.findById(any())).thenReturn(Optional.empty());

        try {
            UUID id = objectFactory.randomUUID();
            TeamDto result = teamService.getTeamById(id);
            assertNull(result);
        } catch (Exception e) {
            assertEquals("Team Not Found", e.getMessage());
        }
    }

    @Test
    void createTeam() {
        Team team = entityFactory.team();
        when(teamRepository.save(any())).thenReturn(team);

        CreateTeamRequest request = objectFactory.teamCreateRequest();
        TeamDto result = teamService.createTeam(request);

        assertNotNull(result);
        assertEquals(request.getName(), result.getName());
    }

    @Test
    void updateTeam() {
        Team team = entityFactory.team();
        when(teamRepository.save(any())).thenReturn(team);

        UpdateTeamRequest request = objectFactory.teamUpdateRequest();
        TeamDto result = teamService.updateTeam(request);

        assertNotNull(result);
        assertEquals(request.getName(), result.getName());
    }

    @Test
    void deleteTeamSuccess() {
        Team team = entityFactory.team();

        when(teamRepository.findById(any())).thenReturn(Optional.of(team));

        UUID id = objectFactory.randomUUID();
        teamService.deleteTeam(id);

        assertNotNull(team.getId());
    }

    @Test
    void deleteTeamFailed() {
        when(teamRepository.findById(any())).thenReturn(Optional.empty());

        try {
            UUID id = objectFactory.randomUUID();
            teamService.deleteTeam(id);
        } catch (Exception e) {
            assertEquals("Team Not Found", e.getMessage());
        }
    }

}