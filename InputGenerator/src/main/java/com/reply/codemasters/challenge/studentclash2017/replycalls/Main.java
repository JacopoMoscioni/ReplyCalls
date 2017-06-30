package com.reply.codemasters.challenge.studentclash2017.replycalls;

import com.google.common.base.Splitter;
import com.google.common.collect.Streams;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.BipartiteVertexFactory;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.CustomerOfficeVertexGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.OfficeConnectionEdgeGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.ReplyOfficeVertexGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.Office;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.OfficeConnection;
import com.reply.codemasters.challenge.studentclash2017.replycalls.options.CommandLineOptions;
import org.apache.commons.cli.*;
import org.jgrapht.Graph;
import org.jgrapht.generate.GnpRandomBipartiteGraphGenerator;
import org.jgrapht.graph.SimpleGraph;

import static com.reply.codemasters.challenge.studentclash2017.replycalls.options.CommandLineOptions.*;

/**
 * Main program for the input generator.
 */
public class Main {

    public static void main(String... argv) {
        final Options commandLineOptions = CommandLineOptions.getSupportedOptions();
        final CommandLineParser parser = new DefaultParser();

        try {
            final CommandLine commandLine = parser.parse(commandLineOptions, argv);


            final Graph<Office, OfficeConnection> officeGraph = generateOfficesGraph(commandLine);

        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage());
            new HelpFormatter().printHelp("java -jar <jarfile>", commandLineOptions);
            System.exit(-1);
        }
    }

    private static Graph<Office, OfficeConnection> generateOfficesGraph(CommandLine commandLine) {
        final int replyOfficesNum = getIntInRange(commandLine.getOptionValue(REPLY_OFFICES), 0, 1000);
        final int customersOfficesNum = getIntInRange(commandLine.getOptionValue(
                CUSTOMER_OFFICES), 0, 1000);
        final double connectionDensity = getDoubleInRange(commandLine.getOptionValue(CONNECTION_DENSITY), 0d, 1d);

        // parameters for Reply offices generation
        final int maxEmployeeNum = getIntInRange(commandLine.getOptionValue(EMPLOYEES, "100"), 0, 1000);

        final int minSoftwareSkillPoints =
                getIntInRange(commandLine.getOptionValue(MIN_SOFTWARE_POINTS, "100"), 0, 1000);
        final int maxSoftwareSkillPoints =
                getIntInRange(commandLine.getOptionValue(MAX_SOFTWARE_POINTS, "1000"), minSoftwareSkillPoints, 1000);

        final int minHardwareSkillPoints =
                getIntInRange(commandLine.getOptionValue(MIN_SOFTWARE_POINTS, "100"), 0, 1000);
        final int maxHardwareSkillPoints =
                getIntInRange(commandLine.getOptionValue(MAX_SOFTWARE_POINTS, "1000"), minHardwareSkillPoints, 1000);

        final int[] fees = getIntArrayInRange(commandLine.getOptionValue(FEES, "100,250,500,750,1000"), 0, 1000);

        // parameters for Reply-Customer connections generation
        final int minHiccups = getIntInRange(commandLine.getOptionValue(MIN_CONNECTION_HICCUPS, "0"), 0, 100);
        final int maxHiccups =
                getIntInRange(commandLine.getOptionValue(MAX_CONNECTION_HICCUPS, "100"), minHiccups, 100);
        final int minConnections = getIntInRange(commandLine.getOptionValue(MIN_CONCURRENT_CONNECTIONS, "0"), 0, 100);
        final int maxConnections =
                getIntInRange(commandLine.getOptionValue(MAX_CONCURRENT_CONNECTIONS, "100"), minConnections, 100);

        final GnpRandomBipartiteGraphGenerator<Office, OfficeConnection> graphGenerator =
                new GnpRandomBipartiteGraphGenerator<>(replyOfficesNum,
                                                       customersOfficesNum,
                                                       connectionDensity);
        final Graph<Office, OfficeConnection> officeGraph = new SimpleGraph<>(
                new OfficeConnectionEdgeGenerator(minHiccups, maxHiccups, minConnections, maxConnections));
        graphGenerator.generateGraph(officeGraph,
                                     new BipartiteVertexFactory(new ReplyOfficeVertexGenerator(maxEmployeeNum,
                                                                                               minSoftwareSkillPoints,
                                                                                               maxSoftwareSkillPoints,
                                                                                               minHardwareSkillPoints,
                                                                                               maxHardwareSkillPoints,
                                                                                               fees),
                                                                new CustomerOfficeVertexGenerator(),
                                                                replyOfficesNum),
                                     null);
        return officeGraph;
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
