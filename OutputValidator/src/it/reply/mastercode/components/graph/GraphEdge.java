package it.reply.mastercode.components.graph;

import it.reply.mastercode.components.office.OfficeCustomer;
import it.reply.mastercode.components.office.OfficeReply;

/**
 * Created by Jacopo Moscioni on 28/06/17.
 */
public class GraphEdge {
    private GraphWeight weight;
    private OfficeReply reply;
    private OfficeCustomer customer;

    public GraphEdge(GraphWeight weight, OfficeReply reply, OfficeCustomer customer) {
        this.weight = weight;
        this.reply = reply;
        this.customer = customer;
    }

    public GraphWeight getWeight() {
        return weight;
    }

    public OfficeReply getReply() {
        return reply;
    }

    public OfficeCustomer getCustomer() {
        return customer;
    }
}
