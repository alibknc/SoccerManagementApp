package com.alibknc.soccermanagement.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDto {

    private UUID id;

    private String name;

    private List<PlayerDto> players;

}
