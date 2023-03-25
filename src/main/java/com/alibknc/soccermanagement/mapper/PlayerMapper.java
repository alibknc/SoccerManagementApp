package com.alibknc.soccermanagement.model.mapper;

import com.alibknc.soccermanagement.model.entity.Player;
import com.alibknc.soccermanagement.model.request.CreatePlayerRequest;
import com.alibknc.soccermanagement.model.response.PlayerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(implementationName = "PlayerMapperImpl", componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(target = "team.players", ignore = true)
    PlayerDto toPlayerDto(Player player);

    Player toPlayerForCreate(CreatePlayerRequest playerRequest);

    Player toPlayerForUpdate(UpdatePlayerRequest playerRequest);

}
