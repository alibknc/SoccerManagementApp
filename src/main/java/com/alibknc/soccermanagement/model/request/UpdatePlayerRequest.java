package com.alibknc.soccermanagement.model.request;

import com.alibknc.soccermanagement.model.type.Position;
import com.alibknc.soccermanagement.model.type.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePlayerRequest {

    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Position position;

    private UUID teamId;

}
