package com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils;

import com.reply.codemasters.challenge.studentclash2017.replycalls.model.CustomerOffice;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.Office;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.OfficeConnection;
import com.reply.codemasters.challenge.studentclash2017.replycalls.model.ReplyOffice;
import org.jgrapht.EdgeFactory;

public class OfficeConnectionEdgeGenerator implements EdgeFactory<Office, OfficeConnection> {
    @Override
    public OfficeConnection createEdge(Office first, Office second) {
        final ReplyOffice replyOffice = (ReplyOffice) (first instanceof ReplyOffice ? first : second);
        final CustomerOffice customerOffice = (CustomerOffice) (first instanceof CustomerOffice ? first : second);
        /*
         * it's impossible that both parameters are of the same type: one of the cast above would have
         * thrown exception.
         */

        return new OfficeConnection(replyOffice, customerOffice);
    }
}
