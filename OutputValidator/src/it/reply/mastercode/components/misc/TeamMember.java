package it.reply.mastercode.components.misc;

import it.reply.mastercode.components.office.OfficeReply;

/**
 * Created by Jacopo Moscioni on 28/06/17.
 */
public class TeamMember {
    private Employee employee;
    private OfficeReply reply;

    public TeamMember(Employee employee, OfficeReply reply) {
        this.employee = employee;
        this.reply = reply;
    }

    public Employee getEmployee() {
        return employee;
    }

    public OfficeReply getReply() {
        return reply;
    }
}
