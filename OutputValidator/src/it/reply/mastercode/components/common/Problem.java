package it.reply.mastercode.components.common;

import it.reply.mastercode.components.common.Points;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class Problem {
    Points difficulty;
    Integer expectedCost;
    Integer workingDaysLeft;

    public Problem(Points difficulty, Integer expectedCost, Integer workingDaysLeft) {
        this.difficulty = difficulty;
        this.expectedCost = expectedCost;
        this.workingDaysLeft = workingDaysLeft;
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
}
