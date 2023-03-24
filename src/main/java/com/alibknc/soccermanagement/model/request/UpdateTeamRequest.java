package com.alibknc.soccermanagement.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateTeamRequest {

    private UUID id;

    private String name;

}
