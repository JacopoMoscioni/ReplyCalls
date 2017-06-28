package it.reply.mastercode.components.misc;

import it.reply.mastercode.components.office.OfficeCustomer;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class Problem {
    private Points difficulty;
    private Integer expectedCost;
    private Integer workingDaysLeft;
    private OfficeCustomer customer;

    public Problem(Points difficulty, Integer expectedCost, Integer workingDaysLeft, OfficeCustomer customer) {
        this.difficulty = difficulty;
        this.expectedCost = expectedCost;
        this.workingDaysLeft = workingDaysLeft;
        this.customer = customer;
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
}
