package it.reply.mastercode.components.common;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class Points {
    Integer software;
    Integer hardware;

    public Points(Integer software) {
        this.software = software;
    }

    public Integer getSoftware() {
        return software;
    }

    public Integer getHardware() {
        return hardware;
    }
}
