package com.reply.codemasters.challenge.studentclash2017.replycalls;

import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.BipartiteVertexFactory;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.CustomerOfficeVertexGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.OfficeConnectionEdgeGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.ReplyOfficeVertexGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.Office;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.OfficeConnection;
import org.apache.commons.cli.*;
import org.jgrapht.Graph;
import org.jgrapht.generate.GnpRandomBipartiteGraphGenerator;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.SimpleGraph;

import java.util.Map;

/**
 * Main program for the input generator.
 */
public class Main {

    public static void main(String... argv) {
        final Options commandLineOptions = defineCommandLineOptions();
        final CommandLineParser parser = new DefaultParser();

        try {
            final CommandLine commandLine = parser.parse(commandLineOptions, argv);
            final int replyOfficesNum = getIntInRange(commandLine.getOptionValue('r'), 0, 1000);
            final int customersOfficesNum = getIntInRange(commandLine.getOptionValue('c'), 0, 1000);
            final double connectionDensity = getDoubleInRange(commandLine.getOptionValue('d'), 0d, 1d);

            final Graph<Office, OfficeConnection> officeGraph = generateOfficesGraph(replyOfficesNum,
                                                                                     customersOfficesNum,
                                                                                     connectionDensity);

            officeGraph.vertexSet().stream().count();
        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage());
            new HelpFormatter().printHelp("java -jar <jarfile>", commandLineOptions);
            System.exit(-1);
        }
    }

    private static Graph<Office, OfficeConnection> generateOfficesGraph(int replyOfficesNum,
                                                                        int customersOfficesNum,
                                                                        double connectionDensity) {
        final GnpRandomBipartiteGraphGenerator graphGenerator
                = new GnpRandomBipartiteGraphGenerator(replyOfficesNum,
                                                       customersOfficesNum,
                                                       connectionDensity);
        final Graph<Office, OfficeConnection> officeGraph = new SimpleGraph<>(new OfficeConnectionEdgeGenerator());
        graphGenerator.generateGraph(officeGraph,
                                     new BipartiteVertexFactory(new ReplyOfficeVertexGenerator(),
                                                                new CustomerOfficeVertexGenerator(),
                                                                replyOfficesNum),
                                     null);
        return officeGraph;
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

        return commandLineOptions;
    }
}
