package com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils;

import com.reply.codemasters.challenge.studentclash2017.replycalls.generators.EmployeeGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.Employee;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.ReplyOffice;
import org.jgrapht.VertexFactory;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ReplyOfficeVertexGenerator implements VertexFactory<ReplyOffice> {
    private final int maxEmployeeNum;
    private final EmployeeGenerator employeeGenerator;

    private final AtomicInteger officeCounter = new AtomicInteger(1);

    public ReplyOfficeVertexGenerator(int maxEmployeeNum,
                                      int minSoftwareSkillPoints,
                                      int maxSoftwareSkillPoints,
                                      int minHardwareSkillPoints, int maxHardwareSkillPoints, int[] fees) {

        this.maxEmployeeNum = maxEmployeeNum;
        this.employeeGenerator =
                new EmployeeGenerator(minSoftwareSkillPoints, maxSoftwareSkillPoints, minHardwareSkillPoints,
                                      maxHardwareSkillPoints, fees);
    }

    @Override
    public ReplyOffice createVertex() {
        return new ReplyOffice(officeCounter.getAndIncrement(),
                               Stream.generate(employeeGenerator)
                                     .limit(ThreadLocalRandom.current().nextInt(1, maxEmployeeNum))
                                     .toArray(Employee[]::new));
    }
}
