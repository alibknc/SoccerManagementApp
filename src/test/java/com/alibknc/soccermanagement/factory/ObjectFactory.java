package com.alibknc.soccermanagement.factory;

import com.alibknc.soccermanagement.model.request.CreatePlayerRequest;
import com.alibknc.soccermanagement.model.request.CreateTeamRequest;
import com.alibknc.soccermanagement.model.request.UpdateTeamRequest;
import com.alibknc.soccermanagement.model.type.Position;
import com.alibknc.soccermanagement.model.type.Status;

import java.util.UUID;

public class ObjectFactory {

    public CreateTeamRequest teamCreateRequest() {
        CreateTeamRequest request = new CreateTeamRequest();
        request.setName("Juventus");
        return request;
    }

    public UpdateTeamRequest teamUpdateRequest() {
        UpdateTeamRequest request = new UpdateTeamRequest();
        request.setId(randomUUID());
        request.setName("Juventus");
        return request;
    }

    public CreatePlayerRequest playerCreateRequest() {
        CreatePlayerRequest request = new CreatePlayerRequest();
        request.setName("Cristiano Ronaldo");
        request.setPosition(Position.GOALKEEPER);
        request.setStatus(Status.FOREIGN);
        request.setTeamId(randomUUID());
        return request;
    }

    public UUID randomUUID() {
        return UUID.randomUUID();
    }

}
