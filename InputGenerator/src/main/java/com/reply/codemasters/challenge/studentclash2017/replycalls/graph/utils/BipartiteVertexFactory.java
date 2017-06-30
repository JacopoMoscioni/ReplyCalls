package com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils;

import com.reply.codemasters.challenge.studentclash2017.replycalls.model.Office;
import lombok.RequiredArgsConstructor;
import org.jgrapht.VertexFactory;

/**
 * Created by L210282 on 30/06/2017.
 */
@RequiredArgsConstructor
public class BipartiteVertexFactory implements VertexFactory<Office> {

    private final VertexFactory<? extends Office> firstPartitionFactory;

    private final VertexFactory<? extends Office> secondPartitionFactory;

    private final int numberOfFirst;

    private int generated = 0;

    @Override
    public Office createVertex() {
        return this.generated++ < numberOfFirst
                ? firstPartitionFactory.createVertex()
                : secondPartitionFactory.createVertex();
    }
}
