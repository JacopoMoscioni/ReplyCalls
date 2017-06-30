package com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils;

import com.reply.codemasters.challenge.studentclash2017.replycalls.model.ReplyOffice;
import org.jgrapht.VertexFactory;

/**
 * Created by L210282 on 30/06/2017.
 */
public class ReplyOfficeVertexGenerator implements VertexFactory<ReplyOffice> {
    @Override
    public ReplyOffice createVertex() {
        return new ReplyOffice();
    }
}
