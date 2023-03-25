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

    @Query("SELECT COUNT(p) FROM Player p WHERE p.team.id=?1")
    int getPlayerCountByTeamId(UUID teamId);

    @Query("SELECT COUNT(p) FROM Player p WHERE p.team.id=?1 AND p.status=?2")
    int getPlayerCountOfTeamByIdAndStatus(UUID teamId, Status status);

    @Query("SELECT COUNT(p) FROM Player p WHERE p.team.id=?1 AND p.position=?2")
    int getPlayerCountOfTeamByIdAndPosition(UUID teamId, Position position);

    @Modifying
    @Query("DELETE FROM Player p WHERE p.team.id=?1")
    void deleteByTeamId(UUID id);

}
