package it.reply.mastercode.components.office;

import it.reply.mastercode.components.misc.Employee;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class OfficeReply extends Office {
    private Integer numeroDipendenti;
    private ArrayList<Employee> dipendentiLista;
    private HashMap<String,Employee> dipendentiMap;

    public OfficeReply(String name) {
        super(name);
        dipendentiLista = new ArrayList<>();
        dipendentiMap = new HashMap<>();
    }

    public Integer getNumeroDipendenti() {
        return numeroDipendenti;
    }

    public void setNumeroDipendenti(Integer numeroDipendenti) {
        this.numeroDipendenti = numeroDipendenti;
    }

    public ArrayList<Employee> getDipendentiLista() {
        return dipendentiLista;
    }

    public HashMap<String, Employee> getDipendentiMap() {
        return dipendentiMap;
    }

}
