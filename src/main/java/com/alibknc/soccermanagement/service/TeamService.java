package com.alibknc.soccermanagement.service;

import com.alibknc.soccermanagement.handler.CustomException;
import com.alibknc.soccermanagement.model.entity.Team;
import com.alibknc.soccermanagement.model.mapper.TeamMapper;
import com.alibknc.soccermanagement.model.request.CreateTeamRequest;
import com.alibknc.soccermanagement.model.request.UpdateTeamRequest;
import com.alibknc.soccermanagement.model.response.TeamDto;
import org.springframework.stereotype.Service;
import com.alibknc.soccermanagement.repository.TeamRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDto> getAllTeams() {
        List<Team> teamList = teamRepository.findAll();

        return teamList.stream()
                .map(TeamMapper.INSTANCE::toTeamDto)
                .collect(Collectors.toList());
    }

    public TeamDto getTeamById(UUID id) {
        return TeamMapper.INSTANCE.toTeamDto(teamRepository.findById(id).orElseThrow(() -> new CustomException("Team Not Found")));
    }

    public TeamDto createTeam(CreateTeamRequest team) {
        Team result = teamRepository.save(TeamMapper.INSTANCE.toTeamForCreate(team));
        return TeamMapper.INSTANCE.toTeamDto(result);
    }

    public TeamDto updateTeam(UpdateTeamRequest team) {
        return TeamMapper.INSTANCE.toTeamDto(teamRepository.save(TeamMapper.INSTANCE.toTeamForUpdate(team)));
    }

    public void deleteTeam(UUID id) {
        teamRepository.deleteById(id);
    }

}
