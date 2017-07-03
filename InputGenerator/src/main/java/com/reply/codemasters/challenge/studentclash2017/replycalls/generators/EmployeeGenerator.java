package com.reply.codemasters.challenge.studentclash2017.replycalls.generators;

import com.reply.codemasters.challenge.studentclash2017.replycalls.model.Employee;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class EmployeeGenerator implements Supplier<Employee> {

    private final int minimumSoftwarePoints;
    private final int maximumSoftwarePoints;
    private final int minimumHardwarePoints;
    private final int maximumHardwarePoints;
    private final int[] possibleFees;

    @Override
    public Employee get() {
        int softwarePoints = ThreadLocalRandom.current().nextInt(minimumSoftwarePoints, maximumSoftwarePoints + 1);
        int hardwarePoints = ThreadLocalRandom.current().nextInt(minimumHardwarePoints, maximumHardwarePoints + 1);
        int feeIndex = ThreadLocalRandom.current().nextInt(possibleFees.length);

        return new Employee(softwarePoints, hardwarePoints, possibleFees[feeIndex]);
    }
}
