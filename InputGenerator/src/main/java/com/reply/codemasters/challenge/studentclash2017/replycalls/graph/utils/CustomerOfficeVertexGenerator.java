package com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils;

import com.reply.codemasters.challenge.studentclash2017.replycalls.model.CustomerOffice;
import org.jgrapht.VertexFactory;

public class CustomerOfficeVertexGenerator implements VertexFactory<CustomerOffice> {
    @Override
    public CustomerOffice createVertex() {
        return new CustomerOffice();
    }
}
