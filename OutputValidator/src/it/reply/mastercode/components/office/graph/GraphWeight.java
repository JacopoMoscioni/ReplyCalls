package it.reply.mastercode.components.office.graph;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class GraphWeight {
    private Integer dailyInterruptions;
    private Integer bandwidth;

    public GraphWeight(Integer dailyInterruptions, Integer bandwidth){
        this.dailyInterruptions = dailyInterruptions;
        this.bandwidth = bandwidth;
    }
    public Integer getDailyInterruptions() {
        return dailyInterruptions;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }
}
