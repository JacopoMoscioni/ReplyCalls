package it.reply.mastercode.components.validator;

/**
 * Created by Jacopo Moscioni on 28/06/17.
 */
public class OutputFirstLine {
    private int customersNumber;
    private int replyNumber;
    private int problemsNumber;

    public OutputFirstLine(int customersNumber, int replyNumber, int problemsNumber) {
        this.customersNumber = customersNumber;
        this.replyNumber = replyNumber;
        this.problemsNumber = problemsNumber;
    }

    public int getCustomersNumber() {
        return customersNumber;
    }

    public int getReplyNumber() {
        return replyNumber;
    }

    public int getProblemsNumber() {
        return problemsNumber;
    }
}
