package it.reply.mastercode.components.office.common;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class Points {
    private Integer software;
    private Integer hardware;

    public Points(Integer software, Integer hardware) {
        this.software = software;
        this.hardware = hardware;
    }

    public Integer getSoftware() {
        return software;
    }

    public Integer getHardware() {
        return hardware;
    }
}
