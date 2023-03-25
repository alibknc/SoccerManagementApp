package com.alibknc.soccermanagement.service;

import com.alibknc.soccermanagement.model.exception.CustomException;
import com.alibknc.soccermanagement.model.entity.Player;
import com.alibknc.soccermanagement.model.entity.Team;
import com.alibknc.soccermanagement.mapper.PlayerMapper;
import com.alibknc.soccermanagement.model.request.CreatePlayerRequest;
import com.alibknc.soccermanagement.model.request.UpdatePlayerRequest;
import com.alibknc.soccermanagement.model.response.PlayerDto;
import com.alibknc.soccermanagement.model.type.Position;
import com.alibknc.soccermanagement.model.type.Status;
import com.alibknc.soccermanagement.repository.TeamRepository;
import org.springframework.stereotype.Service;
import com.alibknc.soccermanagement.repository.PlayerRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public List<PlayerDto> getAllPlayers() {
        List<Player> playerList = playerRepository.findAll();

        return playerList.stream()
                .map(PlayerMapper.INSTANCE::toPlayerDto).toList();
    }

    public PlayerDto getPlayerById(UUID id) {
        return PlayerMapper.INSTANCE.toPlayerDto(playerRepository.findById(id).orElseThrow(() -> new CustomException("Player Not Found")));
    }

    public List<PlayerDto> getPlayersByTeamId(UUID id) {
        List<Player> playerList = playerRepository.getPlayersByTeamId(id);
        return playerList.stream().map(PlayerMapper.INSTANCE::toPlayerDto).toList();
    }

    public PlayerDto createPlayer(CreatePlayerRequest request) {
        int totalPlayerCount = playerRepository.getPlayerCountByTeamId(request.getTeamId());

        if (totalPlayerCount >= 18) {
            throw new CustomException("Maximum Player Limit Reached");
        }

        if (request.getStatus() == Status.FOREIGN) {
            int foreignCount = playerRepository.getPlayerCountByTeamIdAndStatus(request.getTeamId(), request.getStatus());

            if (foreignCount >= 6) {
                throw new CustomException("Maximum Foreign Player Limit Reached");
            }
        }

        if (request.getPosition() == Position.GOALKEEPER) {
            int gkCount = playerRepository.getPlayerCountByTeamIdAndPosition(request.getTeamId(), request.getPosition());

            if (gkCount >= 2) {
                throw new CustomException("Maximum GoalKeeper Player Limit Reached");
            }
        }

        Player player = PlayerMapper.INSTANCE.toPlayerForCreate(request);
        Team team = teamRepository.findById(request.getTeamId()).orElseThrow(() -> new CustomException("Team Not Found"));
        player.setTeam(team);
        player = playerRepository.save(player);
        return PlayerMapper.INSTANCE.toPlayerDto(player);
    }

    public PlayerDto updatePlayer(UpdatePlayerRequest request) {
        Player currentPlayer = playerRepository.findById(request.getId()).orElseThrow(() -> new CustomException("Player Not Available"));

        if (request.getStatus() == Status.FOREIGN && currentPlayer.getStatus() != Status.FOREIGN) {
            int foreignCount = playerRepository.getPlayerCountByTeamIdAndStatus(request.getTeamId(), request.getStatus());

            if (foreignCount >= 6) {
                throw new CustomException("Maximum Foreign Player Limit Reached");
            }
        }

        if (request.getPosition() == Position.GOALKEEPER && currentPlayer.getPosition() != Position.GOALKEEPER) {
            int gkCount = playerRepository.getPlayerCountByTeamIdAndPosition(request.getTeamId(), request.getPosition());

            if (gkCount >= 2) {
                throw new CustomException("Maximum GoalKeeper Player Limit Reached");
            }
        }

        Player player = PlayerMapper.INSTANCE.toPlayerForUpdate(request);
        Team team = teamRepository.findById(request.getTeamId()).orElseThrow(() -> new CustomException("Team Not Found"));
        player.setTeam(team);
        player = playerRepository.save(player);
        return PlayerMapper.INSTANCE.toPlayerDto(player);
    }

    public void deletePlayer(UUID id) {
        playerRepository.deleteById(id);
    }

    @Transactional
    public void deletePlayersTeamId(UUID id) {
        playerRepository.deleteByTeamId(id);
    }

}
