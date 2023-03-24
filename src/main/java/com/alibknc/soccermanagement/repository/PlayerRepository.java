package com.alibknc.soccermanagement.repository;

import com.alibknc.soccermanagement.model.entity.Player;
import com.alibknc.soccermanagement.model.type.Position;
import com.alibknc.soccermanagement.model.type.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {

    @Query("SELECT p FROM Player p WHERE p.team.id=?1")
    List<Player> getByTeamId(UUID teamId);

    @Query("SELECT p FROM Player p WHERE p.team.id=?1 AND p.status=?2")
    List<Player> getPlayersOfTeamByIdAndStatus(UUID teamId, Status status);

    @Query("SELECT p FROM Player p WHERE p.team.id=?1 AND p.position=?2")
    List<Player> getPlayersOfTeamByIdAndPosition(UUID teamId, Position position);

    @Modifying
    @Query("DELETE FROM Player p WHERE p.team.id=?1")
    void deleteByTeamId(UUID id);

}
