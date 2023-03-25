package com.alibknc.soccermanagement.mapper;

import com.alibknc.soccermanagement.model.entity.Player;
import com.alibknc.soccermanagement.model.entity.Team;
import com.alibknc.soccermanagement.model.request.CreateTeamRequest;
import com.alibknc.soccermanagement.model.request.UpdateTeamRequest;
import com.alibknc.soccermanagement.model.response.PlayerDto;
import com.alibknc.soccermanagement.model.response.TeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(implementationName = "TeamMapperImpl", componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    @Mapping(target = "players", qualifiedByName = "playersWithoutTeam")
    TeamDto toTeamDto(Team team);

    @Named("playersWithoutTeam")
    @Mapping(target = "team", ignore = true)
    PlayerDto toPlayerDtoWithoutTeam(Player player);

    Team toTeamForCreate(CreateTeamRequest teamDto);

    Team toTeamForUpdate(UpdateTeamRequest teamDto);

}
