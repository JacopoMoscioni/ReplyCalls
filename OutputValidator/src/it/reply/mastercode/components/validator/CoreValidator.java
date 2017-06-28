package it.reply.mastercode.components.validator;

import it.reply.mastercode.components.graph.GraphEdge;
import it.reply.mastercode.components.graph.GraphWeight;
import it.reply.mastercode.components.misc.Employee;
import it.reply.mastercode.components.misc.Points;
import it.reply.mastercode.components.misc.Problem;
import it.reply.mastercode.components.misc.Team;
import it.reply.mastercode.components.misc.TeamMember;
import it.reply.mastercode.components.office.OfficeCustomer;
import it.reply.mastercode.components.office.OfficeReply;
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
    private int customersNumber;
    private int replyNumber;
    private int problemsNumber;

    private List<Team> teamList = new ArrayList<>();
    private List<Problem> problemList = new ArrayList<>();
    private List<GraphEdge> edgeList = new ArrayList<>();
    //private List<OfficeReply> orList = new ArrayList<>();

    private Map<String,OfficeReply> orMap = new HashMap<>();
    private Map<String,OfficeCustomer> ocMap = new HashMap<>();

    public static void main(String [] args){
        System.out.println("inizio");
        new CoreValidator();
        System.out.println("fine");
    }

    private CoreValidator() {
        parseInputFile();
        parseOutputFile();
    }

    private void parseInputFile(){
        int lineCounter = 0;

        //List<String> input = IOUtils.readFile(Constants.INPUT_PATH_JACOPO_MAC);
        List<String> input = IOUtils.readFile(Constants.INPUT_PATH_JACOPO_WINDOWS);


        String [] header = input.get(lineCounter++).split(Constants.SEPARATOR_SPACE);
        if (header.length == 3) {
            customersNumber = Integer.parseInt(header[0]);
            replyNumber = Integer.parseInt(header[1]);
            problemsNumber = Integer.parseInt(header[2]);
        }
        else {
            System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON LINE: "+(lineCounter-1));
            return;
        }

        //READING EDGES
        for (int c = 0; c < customersNumber; c++){
            for (int R = 0; R < replyNumber; R++) {
                String[] edgeDescriptor = input.get(lineCounter++).split(Constants.SEPARATOR_SPACE);
                if (edgeDescriptor.length == 2) {
                    GraphWeight gw = new GraphWeight(Integer.parseInt(edgeDescriptor[0]), Integer.parseInt(edgeDescriptor[1]));
                    OfficeReply tempR = orMap.get(Constants.PREFIX_REPLY + R);
                    OfficeCustomer tempc = ocMap.get(Constants.PREFIX_CUSTOMER + c);
                    OfficeReply or;
                    OfficeCustomer oc;
                    if (tempR == null) {
                        OfficeReply newOne = new OfficeReply(Constants.PREFIX_REPLY + R);
                        orMap.put(Constants.PREFIX_REPLY + R, newOne);
                        or = newOne;
                    }
                    else {
                        or = tempR;
                    }

                    if (tempc == null) {
                        OfficeCustomer newOne = new OfficeCustomer(Constants.PREFIX_CUSTOMER + c);
                        ocMap.put(Constants.PREFIX_CUSTOMER + c, newOne);
                        oc = newOne;
                    }
                    else {
                        oc = tempc;
                    }
                    GraphEdge edge = new GraphEdge(gw,or,oc);
                    edgeList.add(edge);
                } else {
                    System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON LINE: " + (lineCounter - 1));
                    return;
                }
            }
        }
        //FINISH EDGES

        //READING REPLY OFFICE DESCRIPTION
        if (orMap.size() != replyNumber){
            System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED. REPLY OFFICES COUNT IS NOT VALID");
            return;
        }

        for (int R = 0; R < replyNumber; R++){
            String[] employeeLine = input.get(lineCounter++).split(Constants.SEPARATOR_SPACE);
            OfficeReply or = orMap.get(Constants.PREFIX_REPLY + R);
            if (employeeLine.length == 1){
                or.setNumeroDipendenti(Integer.parseInt(employeeLine[0]));
            }
            else {
                System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON LINE: " + (lineCounter - 1));
                return;
            }
            //READING EMPLOYEES
            if (or.getNumeroDipendenti() > 0){
                ArrayList<Employee> employeeList = new ArrayList<>();
                for (int e = 0; e < or.getNumeroDipendenti(); e++){
                    String[] employeeDescriptor = input.get(lineCounter++).split(Constants.SEPARATOR_SPACE);
                    if (employeeDescriptor.length == 3) {
                        Points p = new Points(Integer.parseInt(employeeDescriptor[0]), Integer.parseInt(employeeDescriptor[1]));
                        Employee employee = new Employee(p, Integer.parseInt(employeeDescriptor[2]),or, Constants.PREFIX_EMPLOYEE + e);
                        //employeeList.add(employee);
                        or.getDipendentiLista().add(employee);
                        or.getDipendentiMap().put(employee.getName(),employee);
                    }
                    else{
                        System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON LINE: " + (lineCounter - 1));
                        return;
                    }
                }
                //or.setDipendentiLista(employeeList);
                //orList.add(or);
                //orMap.put(or.getName(),or);
            }
            else{
                System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON LINE: " + (lineCounter - 1));
                return;
            }
            //FINISH EMPLOYEES
        }

        //FINISH REPLY OFFICES

        //READING PROBLEMS

        //List<Problem> pList = new ArrayList<>();

        for (int p = 0; p < problemsNumber; p++) {
            String[] problemLine = input.get(lineCounter++).split(" ");
            if (problemLine.length == 5) {
                Integer softwareSkill = Integer.parseInt(problemLine[0]);
                Integer hardwareSkill = Integer.parseInt(problemLine[1]);
                Integer timeLeft = Integer.parseInt(problemLine[2]);
                Integer expectedCost = Integer.parseInt(problemLine[3]);
                Integer customerSource = Integer.parseInt(problemLine[4]) - 1;
                Points pt = new Points(softwareSkill,hardwareSkill);
                Problem problem = new Problem(pt, timeLeft, expectedCost, ocMap.get(Constants.PREFIX_CUSTOMER + customerSource));
                problemList.add(problem);
            }
            else{
                System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON LINE: " + (lineCounter - 1));
                return;
            }
        }

        //FINISH PROBLEMS

        edgeList.get(0);

    }

    private void parseOutputFile(){
        int lineCounter = 0;

        //List<String> output = IOUtils.readFile(Constants.OUTPUT_PATH_JACOPO_MAC);
        List<String> output = IOUtils.readFile(Constants.OUTPUT_PATH_JACOPO_WINDOWS);
        int numeroProblemi = output.size();
        if (numeroProblemi != problemList.size()){
            System.out.println("ERROR OCCURRED. OUTPUT FILE CORRUPTED NOT OK. OUTPUT PROBLEMS NUMBER DOES NOT MATCH THE INPUT PROBLEMS NUMBER");
            return;
        }
        else{
            for (int p = 0; p < numeroProblemi; p++){
                String[] teamDescriptor = output.get(lineCounter++).split(Constants.SEPARATOR_SPACE);
                Team team = new Team(new ArrayList<>());
                for (int t = 0; t < teamDescriptor.length; t++){
                    String[] employeeDescriptor = teamDescriptor[t].split(Constants.SEPARATOR_DOT);
                    if (employeeDescriptor.length == 2){
                        Integer ReplyOffice = Integer.parseInt(employeeDescriptor[0]) - 1;
                        Integer employeeNumber = Integer.parseInt(employeeDescriptor[1]) - 1;
                        if (ReplyOffice >= 0 && employeeNumber >= 0){
                            OfficeReply R = orMap.get(Constants.PREFIX_REPLY + ReplyOffice);
                            Employee e = R.getDipendentiMap().get(Constants.PREFIX_EMPLOYEE + employeeNumber);
                            TeamMember tm = new TeamMember(e,R);
                            team.getTeamList().add(tm);
                        }
                        else{
                            System.out.println("ERROR OCCURRED. reply offices and/or employee number cannot be 0");
                            return;
                        }
                        teamList.add(team);
                    }
                    else{
                        System.out.println("ERROR OCCURRED. OUTPUT FILE CORRUPTED ON LINE: " + (lineCounter - 1));
                        return;
                    }

                }

            }
            System.out.println("woooo");
        }

    }
}
