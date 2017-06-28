package it.reply.mastercode.components.office.reply.team;

import it.reply.mastercode.components.office.customer.Problem;

import java.util.List;

/**
 * Created by Jacopo Moscioni on 28/06/17.
 */
public class Team {
    private List<TeamMember> teamList;
    private Problem problem;
    private String name;
    public Team(List<TeamMember> teamList, Problem problem) {
        this.teamList = teamList;
        this.problem = problem;
    }

    public List<TeamMember> getTeamList() {
        return teamList;
    }

    public Problem getProblem() {
        return problem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
