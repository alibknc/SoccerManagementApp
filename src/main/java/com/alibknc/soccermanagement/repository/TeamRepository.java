package com.alibknc.soccermanagement.repository;

import com.alibknc.soccermanagement.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {

}
