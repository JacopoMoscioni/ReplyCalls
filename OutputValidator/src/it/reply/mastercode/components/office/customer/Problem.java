package it.reply.mastercode.components.office.customer;

import it.reply.mastercode.components.office.common.Points;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class Problem {
    private Points difficulty;
    private Integer expectedCost;
    private Integer workingDaysLeft;
    private OfficeCustomer customer;
    private String name;

    public Problem(Points difficulty, Integer expectedCost, Integer workingDaysLeft, OfficeCustomer customer, String name) {
        this.difficulty = difficulty;
        this.expectedCost = expectedCost;
        this.workingDaysLeft = workingDaysLeft;
        this.customer = customer;
        this.name = name;
    }

    public Points getDifficulty() {
        return difficulty;
    }

    public Integer getExpectedCost() {
        return expectedCost;
    }

    public Integer getWorkingDaysLeft() {
        return workingDaysLeft;
    }

    public OfficeCustomer getCustomer() {
        return customer;
    }

    public String getName() {
        return name;
    }
}
