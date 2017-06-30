package com.reply.codemasters.challenge.studentclash2017.replycalls.generators;

import com.reply.codemasters.challenge.studentclash2017.replycalls.model.CustomerOffice;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.Problem;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class ProblemGenerator implements Supplier<Problem> {

    private final List<CustomerOffice> customers;
    private final int minSoftwareSkills;
    private final int maxSoftwareSkills;
    private final int minHardwareSkills;
    private final int maxHardwareSkills;
    private final int minBudget;
    private final int maxBudget;
    private final int minEffort;
    private final int maxEffort;

    @Override
    public Problem get() {
        int customerIndex = ThreadLocalRandom.current().nextInt(customers.size());
        return new Problem(customers.get(customerIndex),
                           ThreadLocalRandom.current().nextInt(minSoftwareSkills, maxSoftwareSkills),
                           ThreadLocalRandom.current().nextInt(minHardwareSkills, maxHardwareSkills),
                           ThreadLocalRandom.current().nextInt(minBudget, maxBudget),
                           ThreadLocalRandom.current().nextInt(minEffort, maxEffort));
    }
}
