package com.reply.codemasters.challenge.studentclash2017.replycalls;

import com.google.common.base.Splitter;
import com.google.common.collect.MoreCollectors;
import com.google.common.collect.Streams;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.BipartiteVertexFactory;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.CustomerOfficeVertexGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.OfficeConnectionEdgeGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.ReplyOfficeVertexGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.Office;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.OfficeConnection;
import org.apache.commons.cli.*;
import org.jgrapht.Graph;
import org.jgrapht.generate.GnpRandomBipartiteGraphGenerator;
import org.jgrapht.graph.SimpleGraph;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Main program for the input generator.
 */
public class Main {

    public static void main(String... argv) {
        final Options commandLineOptions = defineCommandLineOptions();
        final CommandLineParser parser = new DefaultParser();

        try {
            final CommandLine commandLine = parser.parse(commandLineOptions, argv);


            final Graph<Office, OfficeConnection> officeGraph = generateOfficesGraph(commandLine);

            officeGraph.vertexSet().stream().count();
        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage());
            new HelpFormatter().printHelp("java -jar <jarfile>", commandLineOptions);
            System.exit(-1);
        }
    }

    private static Graph<Office, OfficeConnection> generateOfficesGraph(CommandLine commandLine) {
        final int replyOfficesNum = getIntInRange(commandLine.getOptionValue('r'), 0, 1000);
        final int customersOfficesNum = getIntInRange(commandLine.getOptionValue('c'), 0, 1000);
        final double connectionDensity = getDoubleInRange(commandLine.getOptionValue('d'), 0d, 1d);

        final int maxEmployeeNum = getIntInRange(commandLine.getOptionValue('e', "100"), 0, 1000);

        final int minSoftwareSkillPoints = getIntInRange(commandLine.getOptionValue('s', "100"), 0, 1000);
        final int maxSoftwareSkillPoints = getIntInRange(commandLine.getOptionValue('S', "1000"), 0, 1000);

        final int minHardwareSkillPoints = getIntInRange(commandLine.getOptionValue('h', "100"), 0, 1000);
        final int maxHardwareSkillPoints = getIntInRange(commandLine.getOptionValue('H', "1000"), 0, 1000);

        final int[] fees = getIntArrayInRange(commandLine.getOptionValue('f', "100,250,500,750,1000"), 0, 1000);

        final GnpRandomBipartiteGraphGenerator graphGenerator
                = new GnpRandomBipartiteGraphGenerator(replyOfficesNum,
                                                       customersOfficesNum,
                                                       connectionDensity);
        final Graph<Office, OfficeConnection> officeGraph = new SimpleGraph<>(new OfficeConnectionEdgeGenerator());
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

    private static Options defineCommandLineOptions() {
        final Options commandLineOptions = new Options();
        commandLineOptions.addRequiredOption("r", "reply", true, "The number of Reply offices to generate");
        commandLineOptions.addRequiredOption("c", "customers", true, "The number of Customer offices to generate");
        commandLineOptions.addRequiredOption("d",
                                             "density",
                                             true,
                                             "The higher this number, the more connections there will be between Reply and Customer offices");

        commandLineOptions.addOption("e", "employee", true, "Maximum number of employee for each company");

        commandLineOptions.addOption("s", "minSoftPoints", true, "Minimum software skill points for employee");
        commandLineOptions.addOption("S", "maxSoftPoints", true, "Maximum software skill points for employee");
        commandLineOptions.addOption("h", "minHardPoints", true, "Minimum hardware skill points for employee");
        commandLineOptions.addOption("H", "maxHardPoints", true, "Maximum hardware skill points for employee");

        return commandLineOptions;
    }
}
