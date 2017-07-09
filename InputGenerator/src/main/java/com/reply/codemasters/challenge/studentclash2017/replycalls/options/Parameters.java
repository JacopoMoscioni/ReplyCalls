package com.reply.codemasters.challenge.studentclash2017.replycalls.options;

import com.google.common.base.Splitter;
import com.google.common.collect.Streams;
import lombok.Value;
import org.apache.commons.cli.CommandLine;

import static com.reply.codemasters.challenge.studentclash2017.replycalls.options.CommandLineOptions.*;

@Value
public class Parameters {

    private final int replyOfficesNum;
    private final int customersOfficesNum;
    private final double connectionDensity;
    private final int maxEmployeeNum;
    private final int minSoftwareSkillPoints;
    private final int maxSoftwareSkillPoints;
    private final int minHardwareSkillPoints;
    private final int maxHardwareSkillPoints;
    private final int[] fees;
    private final int minHiccups;
    private final int maxHiccups;
    private final int minConnections;
    private final int maxConnections;
    private final int problems;
    private final int minProblemSoftwareSkill;
    private final int maxProblemSoftwareSkill;
    private final int minProblemHardwareSkill;
    private final int maxProblemHardwareSkill;
    private final int minProblemBudget;
    private final int maxProblemBudget;
    private final int minProblemEffort;
    private final int maxProblemEffort;

    public Parameters(CommandLine commandLine) {
        replyOfficesNum = getIntInRange(commandLine.getOptionValue(REPLY_OFFICES), 1, 1_000);
        customersOfficesNum = getIntInRange(commandLine.getOptionValue(
                CUSTOMER_OFFICES), 1, 1_000);
        connectionDensity = getDoubleInRange(commandLine.getOptionValue(CONNECTION_DENSITY, "1"), 0d, 1d);

        // parameters for Reply offices generation
        maxEmployeeNum = getIntInRange(commandLine.getOptionValue(EMPLOYEES, "100"), 0, 1_000);

        minSoftwareSkillPoints = getIntInRange(commandLine.getOptionValue(MIN_SOFTWARE_POINTS, "100"), 0, 1000);
        maxSoftwareSkillPoints =
                getIntInRange(commandLine.getOptionValue(MAX_SOFTWARE_POINTS, "1000"), minSoftwareSkillPoints, 1000);

        minHardwareSkillPoints = getIntInRange(commandLine.getOptionValue(MIN_HARDWARE_POINTS, "100"), 0, 1000);
        maxHardwareSkillPoints =
                getIntInRange(commandLine.getOptionValue(MAX_HARDWARE_POINTS, "1000"), minHardwareSkillPoints, 1000);

        fees = getIntArrayInRange(commandLine.getOptionValue(FEES, "100,250,500,750,1000"), 0, 1000);

        // parameters for Reply-Customer connections generation
        minHiccups = getIntInRange(commandLine.getOptionValue(MIN_CONNECTION_HICCUPS, "0"), 0, 100);
        maxHiccups = getIntInRange(commandLine.getOptionValue(MAX_CONNECTION_HICCUPS, "100"), minHiccups, 100);
        minConnections = getIntInRange(commandLine.getOptionValue(MIN_CONCURRENT_CONNECTIONS, "0"), 0, 100);
        maxConnections =
                getIntInRange(commandLine.getOptionValue(MAX_CONCURRENT_CONNECTIONS, "100"), minConnections, 100);

        // parameters for problem generation
        problems = getIntInRange(commandLine.getOptionValue(PROBLEMS, "100"), 10, 1_000);
        minProblemSoftwareSkill = getIntInRange(
                commandLine.getOptionValue(MIN_PROBLEM_SOFTWARE_POINTS, String.valueOf(4 * minSoftwareSkillPoints)), 0,
                10_000);
        maxProblemSoftwareSkill = getIntInRange(
                commandLine.getOptionValue(MAX_PROBLEM_SOFTWARE_POINTS, String.valueOf(4 * maxSoftwareSkillPoints)), minProblemSoftwareSkill,
                10_000);
        minProblemHardwareSkill = getIntInRange(
                commandLine.getOptionValue(MIN_PROBLEM_HARDWARE_POINTS, String.valueOf(4 * minHardwareSkillPoints)), 0,
                10_000);
        maxProblemHardwareSkill = getIntInRange(
                commandLine.getOptionValue(MAX_PROBLEM_HARDWARE_POINTS, String.valueOf(4 * maxHardwareSkillPoints)), minProblemHardwareSkill,
                10_000);
        minProblemBudget = getIntInRange(
                commandLine.getOptionValue(MIN_PROBLEM_BUDGETS, "100"), 0, 10_000);
        maxProblemBudget = getIntInRange(
                commandLine.getOptionValue(MAX_PROBLEM_BUDGETS, "1000"), minProblemBudget, 100_000);
        minProblemEffort = getIntInRange(
                commandLine.getOptionValue(MIN_PROBLEM_EFFORT, "10"), 0, 100);
        maxProblemEffort = getIntInRange(
                commandLine.getOptionValue(MAX_PROBLEM_EFFORT, "1000"), minProblemEffort, 100_000);
    }

    private static int[] getIntArrayInRange(String value, int minimum, int maximum) {
        final int[] ints = Streams.stream(Splitter.on(',').omitEmptyStrings().trimResults().split(value))
                                  .mapToInt(Integer::parseInt)
                                  .toArray();

        for (int current : ints) {
            if (current < minimum || current > maximum) {
                throw new IllegalArgumentException(
                        "Value " + value + " outside validity range [" + minimum + ", " + maximum + "]");
            }
        }

        return ints;
    }

    private static double getDoubleInRange(String value, double minimum, double maximum) {
        double val = Double.parseDouble(value);
        if (val < minimum || val > maximum) {
            throw new IllegalArgumentException(
                    "Value " + value + " outside validity range [" + minimum + ", " + maximum + "]");
        }

        return val;
    }

    private static int getIntInRange(String value, int minimum, int maximum) {
        int val = Integer.parseInt(value);
        if (val < minimum || val > maximum) {
            throw new IllegalArgumentException(
                    "Value " + value + " outside validity range [" + minimum + ", " + maximum + "]");
        }

        return val;
    }
}
