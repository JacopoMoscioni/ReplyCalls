package com.reply.codemasters.challenge.studentclash2017.replycalls;

import com.google.common.base.Predicates;
import com.reply.codemasters.challenge.studentclash2017.replycalls.generators.ProblemGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.BipartiteVertexFactory;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.CustomerOfficeVertexGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.OfficeConnectionEdgeGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils.ReplyOfficeVertexGenerator;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.*;
import com.reply.codemasters.challenge.studentclash2017.replycalls.options.CommandLineOptions;
import com.reply.codemasters.challenge.studentclash2017.replycalls.options.Parameters;
import org.apache.commons.cli.*;
import org.jgrapht.Graph;
import org.jgrapht.generate.GnpRandomBipartiteGraphGenerator;
import org.jgrapht.graph.SimpleGraph;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Main program for the input generator.
 */
public class Main {

    public static void main(String... argv) {
        final Options commandLineOptions = CommandLineOptions.getSupportedOptions();
        final CommandLineParser parser = new DefaultParser();

        try {
            final Parameters commandLine = new Parameters(parser.parse(commandLineOptions, argv));

            final Graph<Office, OfficeConnection> officeGraph = generateOfficesGraph(commandLine);

            final Stream<Problem> problems =
                    generateProblems(officeGraph.vertexSet()
                                                .stream()
                                                .filter(Predicates.instanceOf(CustomerOffice.class))
                                                .map(CustomerOffice.class::cast)
                                                .collect(Collectors.toList()),
                                     commandLine);

            printProblemStatement(officeGraph, problems, commandLine);
        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage());
            new HelpFormatter().printHelp("java -jar <jarfile>", commandLineOptions);
            System.exit(-1);
        }
    }

    private static void printProblemStatement(Graph<Office, OfficeConnection> officeGraph,
                                              Stream<Problem> problems,
                                              Parameters commandLine) {
        // print first line
        System.out.printf("%d %d %d\n", commandLine.getReplyOfficesNum(), commandLine.getCustomersOfficesNum(),
                          commandLine.getProblems());

        // print connections information
        for (OfficeConnection officeConnection : officeGraph.edgeSet()) {
            System.out.printf("%d %d %d %d\n",
                              officeConnection.getReplyOffice().getId(),
                              officeConnection.getCustomerOffice().getId(),
                              officeConnection.getNetworkHiccups(),
                              officeConnection.getConcurrentConnections());
        }

        // print Reply offices data
        officeGraph.vertexSet().stream()
                   .filter(Predicates.instanceOf(ReplyOffice.class))
                   .map(ReplyOffice.class::cast)
                   .forEach((replyOffice -> {
                       System.out.println(replyOffice.getEmployees().length);
                       for (final Employee employee : replyOffice.getEmployees()) {
                           System.out.printf("%d %d %d\n",
                                             employee.getSoftwareSkills(),
                                             employee.getHardwareSkills(),
                                             employee.getFee());
                       }
                   }));

        // print problems
        problems.forEach((problem -> System.out.printf("%d %d %d %d %d\n",
                                                   problem.getSoftwareSkills(),
                                                   problem.getHardwareSkills(),
                                                   problem.getEstimatedEffort(),
                                                   problem.getBudget(),
                                                   problem.getIssuer().getId())));
    }

    private static Stream<Problem> generateProblems(List<CustomerOffice> customers, Parameters commandLine) {
        return Stream.generate(new ProblemGenerator(customers, commandLine.getMinProblemSoftwareSkill(),
                                                    commandLine.getMaxProblemSoftwareSkill(),
                                                    commandLine.getMinProblemHardwareSkill(),
                                                    commandLine.getMaxProblemHardwareSkill(),
                                                    commandLine.getMinProblemBudget(),
                                                    commandLine.getMaxProblemBudget(),
                                                    commandLine.getMinProblemEffort(),
                                                    commandLine.getMaxProblemEffort()))
                     .limit(commandLine.getProblems());
    }

    private static Graph<Office, OfficeConnection> generateOfficesGraph(Parameters commandLine) {
        final GnpRandomBipartiteGraphGenerator<Office, OfficeConnection> graphGenerator =
                new GnpRandomBipartiteGraphGenerator<>(commandLine.getReplyOfficesNum(),
                                                       commandLine.getCustomersOfficesNum(),
                                                       commandLine.getConnectionDensity());
        final Graph<Office, OfficeConnection> officeGraph = new SimpleGraph<>(
                new OfficeConnectionEdgeGenerator(commandLine.getMinHiccups(), commandLine.getMaxHiccups(),
                                                  commandLine.getMinConnections(), commandLine.getMaxConnections()));
        graphGenerator.generateGraph(officeGraph,
                                     new BipartiteVertexFactory(
                                             new ReplyOfficeVertexGenerator(commandLine.getMaxEmployeeNum(),
                                                                            commandLine.getMinSoftwareSkillPoints(),
                                                                            commandLine.getMaxSoftwareSkillPoints(),
                                                                            commandLine.getMinHardwareSkillPoints(),
                                                                            commandLine.getMaxHardwareSkillPoints(),
                                                                            commandLine.getFees()),
                                             new CustomerOfficeVertexGenerator(),
                                             commandLine.getReplyOfficesNum()),
                                     null);
        return officeGraph;
    }

}
