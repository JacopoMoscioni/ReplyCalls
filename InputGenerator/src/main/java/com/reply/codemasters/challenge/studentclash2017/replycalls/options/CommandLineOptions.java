package com.reply.codemasters.challenge.studentclash2017.replycalls.options;

import lombok.experimental.UtilityClass;
import org.apache.commons.cli.Options;

@UtilityClass
public class CommandLineOptions {
    public static final char REPLY_OFFICES = 'r';
    public static final char CUSTOMER_OFFICES = 'c';
    public static final char CONNECTION_DENSITY = 'd';
    public static final char PROBLEMS = 'p';
    public static final char EMPLOYEES = 'e';
    public static final char MIN_SOFTWARE_POINTS = 's';
    public static final char MAX_SOFTWARE_POINTS = 'S';
    public static final char MIN_HARDWARE_POINTS = 'h';
    public static final char MAX_HARDWARE_POINTS = 'H';
    public static final char FEES = 'f';
    public static final String MIN_CONNECTION_HICCUPS = "hc";
    public static final String MAX_CONNECTION_HICCUPS = "HC";
    public static final String MIN_CONCURRENT_CONNECTIONS = "cn";
    public static final String MAX_CONCURRENT_CONNECTIONS = "CN";
    public static final String MIN_PROBLEM_SOFTWARE_POINTS = "ps";
    public static final String MAX_PROBLEM_SOFTWARE_POINTS = "PS";
    public static final String MIN_PROBLEM_HARDWARE_POINTS = "ph";
    public static final String MAX_PROBLEM_HARDWARE_POINTS = "PH";
    public static final String MIN_PROBLEM_BUDGETS = "pb";
    public static final String MAX_PROBLEM_BUDGETS = "PB";
    public static final String MIN_PROBLEM_EFFORT = "pmd";
    public static final String MAX_PROBLEM_EFFORT = "PMD";

    public static Options getSupportedOptions() {
        final Options commandLineOptions = new Options();
        commandLineOptions.addRequiredOption("" + REPLY_OFFICES, "reply", true,
                                             "The number of Reply offices to generate");
        commandLineOptions.addRequiredOption("" + CUSTOMER_OFFICES, "customers", true,
                                             "The number of Customer offices to generate");
        commandLineOptions.addRequiredOption("" + CONNECTION_DENSITY,
                                             "density",
                                             true,
                                             "The higher this number, the more connections there will be between " +
                                                     "Reply and Customer offices");
        commandLineOptions.addRequiredOption("" + PROBLEMS, "problems", true,
                                             "The number of problems to generate");

        commandLineOptions.addOption("" + PROBLEMS, "problems", true, "The number of problems to generate");
        commandLineOptions.addOption("" + EMPLOYEES, "employee", true, "Maximum number of employee for each company");

        // Employees
        commandLineOptions.addOption("" + MIN_SOFTWARE_POINTS, "minSoftPoints", true,
                                     "Minimum software skill points for employee");
        commandLineOptions.addOption("" + MAX_SOFTWARE_POINTS, "maxSoftPoints", true,
                                     "Maximum software skill points for employee");
        commandLineOptions.addOption("" + MIN_HARDWARE_POINTS, "minHardPoints", true,
                                     "Minimum hardware skill points for employee");
        commandLineOptions.addOption("" + MAX_HARDWARE_POINTS, "maxHardPoints", true,
                                     "Maximum hardware skill points for employee");
        commandLineOptions.addOption("" + FEES, "fees", true,
                                     "Comma-separated list of possible employee fee");

        // Connections
        commandLineOptions.addOption(MIN_CONNECTION_HICCUPS, "min-hiccups", true,
                                     "Minimum number of connection drops per day");
        commandLineOptions.addOption(MAX_CONNECTION_HICCUPS, "max-hiccups", true,
                                     "Maximum number of connection drops per day");
        commandLineOptions.addOption(MIN_CONCURRENT_CONNECTIONS, "min-connections", true,
                                     "Minimum number of concurrent connections");
        commandLineOptions.addOption(MAX_CONCURRENT_CONNECTIONS, "max-connections", true,
                                     "Maximum number of concurrent connections");

        // Problems
        commandLineOptions.addOption("" + MIN_PROBLEM_SOFTWARE_POINTS, "minProblemSoftPoints", true,
                                     "Minimum software skill points for problems");
        commandLineOptions.addOption("" + MAX_PROBLEM_SOFTWARE_POINTS, "maxProblemSoftPoints", true,
                                     "Maximum software skill points for problems");
        commandLineOptions.addOption("" + MIN_PROBLEM_HARDWARE_POINTS, "minProblemHardPoints", true,
                                     "Minimum hardware skill points for problems");
        commandLineOptions.addOption("" + MAX_PROBLEM_HARDWARE_POINTS, "maxProblemHardPoints", true,
                                     "Maximum hardware skill points for problems");
        commandLineOptions.addOption("" + MIN_PROBLEM_BUDGETS, "minProblemBudgets", true,
                                     "Minimum budget for problems");
        commandLineOptions.addOption("" + MAX_PROBLEM_BUDGETS, "maxProblemBudgets", true,
                                     "Maximum budget for problems");
        commandLineOptions.addOption("" + MIN_PROBLEM_EFFORT, "minProblemEffort", true,
                                     "Minimum estimated effort for problems");
        commandLineOptions.addOption("" + MAX_PROBLEM_EFFORT, "maxProblemEffort", true,
                                     "Maximum estimated effort for problems");

        return commandLineOptions;
    }
}
