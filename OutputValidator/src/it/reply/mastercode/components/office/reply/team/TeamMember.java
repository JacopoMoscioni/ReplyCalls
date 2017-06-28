package it.reply.mastercode.components.office.reply.team;

import it.reply.mastercode.components.office.graph.GraphEdge;
import it.reply.mastercode.components.office.reply.OfficeReply;

/**
 * Created by Jacopo Moscioni on 28/06/17.
 */
public class TeamMember {
    private Employee employee;
    private OfficeReply reply;
    private GraphEdge edge;

    public TeamMember(Employee employee, OfficeReply reply, GraphEdge edge) {
        this.employee = employee;
        this.reply = reply;
        this.edge = edge;
    }

    public Employee getEmployee() {
        return employee;
    }

    public OfficeReply getReply() {
        return reply;
    }

    public GraphEdge getEdge() {
        return edge;
    }
}
