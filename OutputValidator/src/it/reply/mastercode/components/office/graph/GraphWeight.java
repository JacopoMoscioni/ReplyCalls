package it.reply.mastercode.components.office.graph;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class GraphWeight {
    private Integer dailyInterruptions;
    private Integer bandwidth;
    private Integer bandwidthForValuation;

    public GraphWeight(Integer dailyInterruptions, Integer bandwidth){
        this.dailyInterruptions = dailyInterruptions;
        this.bandwidth = bandwidth;
        this.bandwidthForValuation = bandwidth;
    }
    public Integer getDailyInterruptions() {
        return dailyInterruptions;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    //for valuating purposes
    public boolean decrementBandWidth(){
        if (bandwidthForValuation > 0) {
            bandwidthForValuation--;
            return true;
        }
        return false;
    }

    public boolean isBandwidthStillAvailable(){
        return bandwidthForValuation > 0;
    }
    public void restoreBandWidth(){
        bandwidthForValuation = bandwidth;

    }
}
