package it.reply.mastercode;

import it.reply.mastercode.components.graph.GraphEdge;
import it.reply.mastercode.components.graph.GraphWeight;
import it.reply.mastercode.components.misc.Employee;
import it.reply.mastercode.components.misc.Points;
import it.reply.mastercode.components.misc.Problem;
import it.reply.mastercode.components.office.Office;
import it.reply.mastercode.components.office.OfficeCustomer;
import it.reply.mastercode.components.office.OfficeReply;
import it.reply.mastercode.components.validator.OutputFirstLine;
import it.reply.mastercode.utilities.Constants;
import it.reply.mastercode.utilities.IOUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class CoreValidator {
    private OutputFirstLine ofl;
    private Map<String,OfficeReply> orMap = new HashMap<>();
    private Map<String,OfficeCustomer> ocMap = new HashMap<>();

    private CoreValidator() {
        int counter = 0;
        List<String> input = IOUtils.readInputFile(Constants.INPUT_PATH);
        String [] header = input.get(counter++).split(" ");
        //System.out.println(file.toArray().toString());
        if (header.length == 3) {
            ofl = new OutputFirstLine(Integer.parseInt(header[0]),Integer.parseInt(header[1]),Integer.parseInt(header[2]));
        }
        else {
            System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON LINE: "+(counter-1));
            return;
        }

        //READING EDGES
        List<GraphEdge> edgeList = new ArrayList<>();
        //for(int i = counter; i < edgeNumber; i++){
        for (int c = 0; c < ofl.getCustomersNumber(); c++){
            for (int r = 0; r <ofl.getReplyNumber(); r++) {
                String[] edgeDescriptor = input.get(counter++).split(" ");
                if (edgeDescriptor.length == 2) {
                    GraphWeight gw = new GraphWeight(Integer.parseInt(edgeDescriptor[0]), Integer.parseInt(edgeDescriptor[1]));
                    OfficeReply tempR = orMap.get("R"+r);
                    OfficeCustomer tempc = ocMap.get("c"+c);
                    OfficeReply or;
                    OfficeCustomer oc;
                    if (tempR == null) {
                        OfficeReply newOne = new OfficeReply("R" + r);
                        orMap.put("R" + r, newOne);
                        or = newOne;
                    }
                    else
                        or = tempR;

                    if (tempc == null) {
                        OfficeCustomer newOne = new OfficeCustomer("c" + c);
                        ocMap.put("c" + c, newOne);
                        oc = newOne;
                    }
                    else
                        oc = tempc;
                    GraphEdge edge = new GraphEdge(gw,or,oc);
                    edgeList.add(edge);
                } else {
                    System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON LINE: " + (counter - 1));
                    return;
                }
            }
        }
        //FINISH EDGES

        //READING REPLY OFFICE DESCRIPTION
        List<OfficeReply> orList = new ArrayList<>();
        if (orMap.size() != ofl.getReplyNumber()){
            System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED. REPLY OFFICES COUNT IS NOT VALID");
            return;
        }

        for (int r = 0; r < ofl.getReplyNumber(); r++){
            String[] employeeLine = input.get(counter++).split(" ");
            OfficeReply or = orMap.get("R"+r);
            if (employeeLine.length == 1){
                or.setNumeroDipendenti(Integer.parseInt(employeeLine[0]));
            }
            else {
                System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON LINE: " + (counter - 1));
                return;
            }
            //READING EMPLOYEES
            if (or.getNumeroDipendenti() > 0){
                ArrayList<Employee> employeeList = new ArrayList<>();
                for (int e = 0; e < or.getNumeroDipendenti(); e++){
                    String[] employeeDescriptor = input.get(counter++).split(" ");
                    if (employeeDescriptor.length == 3) {
                        Points p = new Points(Integer.parseInt(employeeDescriptor[0]), Integer.parseInt(employeeDescriptor[1]));
                        Employee employee = new Employee(p, Integer.parseInt(employeeDescriptor[2]),or,""+e);
                        employeeList.add(employee);
                    }
                    else{
                        System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON LINE: " + (counter - 1));
                        return;
                    }
                }
                or.setDipendentiLista(employeeList);
                orList.add(or);
            }
            else{
                System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON LINE: " + (counter - 1));
                return;
            }
            //FINISH EMPLOYEES
        }

        //FINISH REPLY OFFICES

        //READING PROBLEMS

        List<Problem> pList = new ArrayList<>();

        for (int p = 0; p < ofl.getProblemsNumber(); p++) {
            String[] problemLine = input.get(counter++).split(" ");
            if (problemLine.length == 5) {
                Points pt = new Points(Integer.parseInt(problemLine[0]), Integer.parseInt(problemLine[1]));
                Problem problem = new Problem(pt, Integer.parseInt(problemLine[2]), Integer.parseInt(problemLine[3]), ocMap.get("c"+(Integer.parseInt(problemLine[4])-1)));
                pList.add(problem);
            }
            else{
                System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON LINE: " + (counter - 1));
                return;
            }
        }

        //FINISH PROBLEMS
        edgeList.get(0);

    }

    public static void main(String [] args){
        // String header = IOUtils.readFileHeader(Constants.INPUT_PATH);
      new CoreValidator();
      System.out.println("fine");
    }
}
