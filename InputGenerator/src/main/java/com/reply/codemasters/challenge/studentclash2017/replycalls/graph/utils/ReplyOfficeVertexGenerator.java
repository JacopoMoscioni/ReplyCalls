package com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils;

import com.reply.codemasters.challenge.studentclash2017.replycalls.model.Employee;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.ReplyOffice;
import org.jgrapht.VertexFactory;

public class ReplyOfficeVertexGenerator implements VertexFactory<ReplyOffice> {
    private final int maxEmployeeNum;
    private final int minSoftwareSkillPoints;
    private final int maxSoftwareSkillPoints;
    private final int minHardwareSkillPoints;
    private final int maxHardwareSkillPoints;
    private final int[] fees;

    public ReplyOfficeVertexGenerator(int maxEmployeeNum,
                                      int minSoftwareSkillPoints,
                                      int maxSoftwareSkillPoints,
                                      int minHardwareSkillPoints, int maxHardwareSkillPoints, int[] fees) {

        this.maxEmployeeNum = maxEmployeeNum;
        this.minSoftwareSkillPoints = minSoftwareSkillPoints;
        this.maxSoftwareSkillPoints = maxSoftwareSkillPoints;
        this.minHardwareSkillPoints = minHardwareSkillPoints;
        this.maxHardwareSkillPoints = maxHardwareSkillPoints;
        this.fees = fees;
    }

    @Override
    public ReplyOffice createVertex() {
        // TODO implementare la generazione dei dipendenti.
        return new ReplyOffice(new Employee[0]);
    }
}
