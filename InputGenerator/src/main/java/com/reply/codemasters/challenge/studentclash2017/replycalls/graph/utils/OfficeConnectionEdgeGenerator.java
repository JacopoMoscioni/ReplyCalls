package com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils;

import com.reply.codemasters.challenge.studentclash2017.replycalls.model.CustomerOffice;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.Office;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.OfficeConnection;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.ReplyOffice;
import org.jgrapht.EdgeFactory;

import java.util.concurrent.ThreadLocalRandom;

public class OfficeConnectionEdgeGenerator implements EdgeFactory<Office, OfficeConnection> {
    private final int minHiccups;
    private final int maxHiccups;
    private final int minConnections;
    private final int maxConnections;

    public OfficeConnectionEdgeGenerator(int minHiccups, int maxHiccups, int minConnections, int maxConnections) {
        this.minHiccups = minHiccups;
        this.maxHiccups = maxHiccups;
        this.minConnections = minConnections;
        this.maxConnections = maxConnections;
    }

    @Override
    public OfficeConnection createEdge(Office first, Office second) {
        final ReplyOffice replyOffice = (ReplyOffice) (first instanceof ReplyOffice ? first : second);
        final CustomerOffice customerOffice = (CustomerOffice) (first instanceof CustomerOffice ? first : second);
        /*
         * it's impossible that both parameters are of the same type: one of the cast above would have
         * thrown exception.
         */

        final int hiccups = ThreadLocalRandom.current().nextInt(minHiccups, maxHiccups);
        final int concurrentConnections = ThreadLocalRandom.current().nextInt(minConnections, maxConnections);

        return new OfficeConnection(replyOffice, customerOffice, hiccups, concurrentConnections);
    }
}
