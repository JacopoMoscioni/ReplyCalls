package it.reply.mastercode.components.office;

import it.reply.mastercode.components.misc.Employee;

import java.util.ArrayList;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class OfficeReply extends Office {
    private Integer numeroDipendenti;
    private ArrayList<Employee> dipendentiLista;

    public OfficeReply(String name) {
        super(name);
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

    public void setDipendentiLista(ArrayList<Employee> dipendentiLista) {
        this.dipendentiLista = dipendentiLista;
    }
}
