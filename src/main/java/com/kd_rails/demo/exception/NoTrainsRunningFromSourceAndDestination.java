package com.kd_rails.demo.exception;

public class NoTrainsRunningFromSourceAndDestination extends RuntimeException {
    private String source;
    private String destination;

    public NoTrainsRunningFromSourceAndDestination(String source, String destination) {
        super();
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}
