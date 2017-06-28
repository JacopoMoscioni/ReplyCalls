package it.reply.mastercode.components.office.reply.team;

import it.reply.mastercode.components.office.customer.Problem;

import java.util.List;

/**
 * Created by Jacopo Moscioni on 28/06/17.
 */
public class Team {
    private List<TeamMember> teamMemberList;
    private Problem problem;
    private String name;
    public Team(List<TeamMember> teamMemberList, Problem problem) {
        this.teamMemberList = teamMemberList;
        this.problem = problem;
    }

    public List<TeamMember> getTeamMemberList() {
        return teamMemberList;
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
