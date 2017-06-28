package it.reply.mastercode.components.common;

import it.reply.mastercode.components.common.Points;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class Employee {
    Points skills;
    Integer money;

    public Employee(Points skills, Integer money) {
        this.skills = skills;
        this.money = money;
    }

    public Points getSkills() {
        return skills;
    }

    public Integer getMoney() {
        return money;
    }
}
