package it.reply.mastercode.components.office.graph;

import it.reply.mastercode.components.office.customer.OfficeCustomer;
import it.reply.mastercode.components.office.reply.OfficeReply;

/**
 * Created by Jacopo Moscioni on 28/06/17.
 */
public class GraphEdge {
    private GraphWeight weight;
    private OfficeReply reply;
    private OfficeCustomer customer;
    private String name;

    public GraphEdge(GraphWeight weight, OfficeReply reply, OfficeCustomer customer) {
        this.weight = weight;
        this.reply = reply;
        this.customer = customer;
        name = reply.getName() + customer.getName();
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

    public String getName() {
        return name;
    }
}
