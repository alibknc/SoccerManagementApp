package com.alibknc.soccermanagement.factory;

import com.alibknc.soccermanagement.model.entity.Player;
import com.alibknc.soccermanagement.model.entity.Team;
import com.alibknc.soccermanagement.model.type.Position;
import com.alibknc.soccermanagement.model.type.Status;

import java.util.ArrayList;
import java.util.List;

public class EntityFactory {

    public Team team() {
        Team team = new Team();
        team.setName("Juventus");
        team.setPlayers(new ArrayList<>());
        return team;
    }

    public Player player() {
        Player player = new Player();
        player.setName("Cristiano Ronaldo");
        player.setTeam(team());
        player.setPosition(Position.GOALKEEPER);
        player.setStatus(Status.FOREIGN);
        return player;
    }

    public List<Team> teamList() {
        List<Team> list = new ArrayList<>();
        list.add(team());
        list.add(team());
        list.add(team());
        return list;
    }

    public List<Player> playerList() {
        List<Player> list = new ArrayList<>();
        list.add(player());
        list.add(player());
        list.add(player());
        return list;
    }

    public List<Player> generatePlayerList(int i) {
        var list = new ArrayList<Player>(i);
        for (int j = 0; j < i; j++) {
            list.add(player());
        }

        return list;
    }

}
