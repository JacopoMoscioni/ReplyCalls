package com.reply.codemasters.challenge.studentclash2017.replycalls.graph.utils;

import com.reply.codemasters.challenge.studentclash2017.replycalls.model.CustomerOffice;
import org.jgrapht.VertexFactory;

/**
 * Created by L210282 on 30/06/2017.
 */
public class CustomerOfficeVertexGenerator implements VertexFactory<CustomerOffice> {
    @Override
    public CustomerOffice createVertex() {
        return new CustomerOffice();
    }
}
