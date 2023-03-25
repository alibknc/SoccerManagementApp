package com.alibknc.soccermanagement.repository;

import com.alibknc.soccermanagement.model.entity.Player;
import com.alibknc.soccermanagement.model.type.Position;
import com.alibknc.soccermanagement.model.type.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {

    @Query("SELECT p FROM Player p WHERE p.team.id=:teamId")
    List<Player> getPlayersByTeamId(@Param("teamId") UUID teamId);

    @Query("SELECT COUNT(p) FROM Player p WHERE p.team.id=:teamId")
    int getPlayerCountByTeamId(@Param("teamId") UUID teamId);

    @Query("SELECT COUNT(p) FROM Player p WHERE p.team.id=:teamId AND p.status=:status")
    int getPlayerCountByTeamIdAndStatus(@Param("teamId") UUID teamId, @Param("status") Status status);

    @Query("SELECT COUNT(p) FROM Player p WHERE p.team.id=:teamId AND p.position=:position")
    int getPlayerCountByTeamIdAndPosition(@Param("teamId") UUID teamId, @Param("position") Position position);

    @Modifying
    @Query("DELETE FROM Player p WHERE p.team.id=:teamId")
    void deleteByTeamId(@Param("teamId") UUID id);

}
