package it.reply.mastercode.components.misc;

import it.reply.mastercode.components.office.OfficeReply;

import java.util.List;

/**
 * Created by Jacopo Moscioni on 28/06/17.
 */
public class Team {
    private List<TeamMember> teamList;

    public Team(List<TeamMember> teamList) {
        this.teamList = teamList;
    }

    public List<TeamMember> getTeamList() {
        return teamList;
    }
}
