package it.reply.mastercode.components.misc;

import it.reply.mastercode.components.office.OfficeReply;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class Employee {
    private OfficeReply employer;
    private String name;
    private Points skills;
    private Integer money;

    public Employee(Points skills, Integer money, OfficeReply employer, String name) {
        this.skills = skills;
        this.money = money;
        this.employer = employer;
        this.name = name;
    }

    public Points getSkills() {
        return skills;
    }

    public Integer getMoney() {
        return money;
    }

    public OfficeReply getEmployer() {
        return employer;
    }

    public String getName() {
        return name;
    }
}
