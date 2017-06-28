package it.reply.mastercode.utilities;

import it.reply.mastercode.components.graph.GraphEdge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class IOUtils {
    public static List<String> readFile(final String filename) {
        List<String> lines = new ArrayList<>();
        try(Stream<String> stream = Files.lines(Paths.get(filename))) {
            lines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
