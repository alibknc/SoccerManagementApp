package com.alibknc.soccermanagement.service;

import com.alibknc.soccermanagement.model.exception.CustomException;
import com.alibknc.soccermanagement.model.entity.Team;
import com.alibknc.soccermanagement.mapper.TeamMapper;
import com.alibknc.soccermanagement.model.request.CreateTeamRequest;
import com.alibknc.soccermanagement.model.request.UpdateTeamRequest;
import com.alibknc.soccermanagement.model.response.TeamDto;
import org.springframework.stereotype.Service;
import com.alibknc.soccermanagement.repository.TeamRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDto> getAllTeams() {
        List<Team> teamList = teamRepository.findAll();

        return teamList.stream()
                .map(TeamMapper.INSTANCE::toTeamDto).toList();
    }

    public TeamDto getTeamById(UUID id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new CustomException("Team Not Found"));
        return TeamMapper.INSTANCE.toTeamDto(team);
    }

    public TeamDto createTeam(CreateTeamRequest request) {
        Team team = teamRepository.save(TeamMapper.INSTANCE.toTeamForCreate(request));
        return TeamMapper.INSTANCE.toTeamDto(team);
    }

    public TeamDto updateTeam(UpdateTeamRequest request) {
        Team team = teamRepository.save(TeamMapper.INSTANCE.toTeamForUpdate(request));
        return TeamMapper.INSTANCE.toTeamDto(team);
    }

    public void deleteTeam(UUID id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new CustomException("Team Not Found"));
        teamRepository.delete(team);
    }

}
