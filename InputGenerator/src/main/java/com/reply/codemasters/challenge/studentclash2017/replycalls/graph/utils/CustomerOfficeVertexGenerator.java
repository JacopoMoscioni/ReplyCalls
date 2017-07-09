package com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils;

import com.reply.codemasters.challenge.studentclash2017.replycalls.model.CustomerOffice;
import org.jgrapht.VertexFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class CustomerOfficeVertexGenerator implements VertexFactory<CustomerOffice> {

    private final AtomicInteger officeCounter = new AtomicInteger(1);

    @Override
    public CustomerOffice createVertex() {
        return new CustomerOffice(officeCounter.getAndIncrement());
    }
}
