package it.reply.mastercode.components.office;

import it.reply.mastercode.components.common.Employee;

import java.util.ArrayList;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class OfficeReply extends Office {
    private Integer numeroDipendenti;
    private ArrayList<Employee> employee;

    public OfficeReply(String name) {
        super(name);
    }

}
