package escalonador;

public class Processo {
    private final String id;
    private int arrival;
    private int executionTimeUnits;
    private int executedTimeUnits;

    public Processo(String id, int arrival, int executionTimeUnits) {
        this.id = id;
        this.arrival = arrival;
        this.executionTimeUnits = executionTimeUnits;
    }

    public String getId() {
        return id;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public int getExecutionTimeUnits() {
        return executionTimeUnits;
    }

    public void setExecutionTimeUnits(int executionTimeUnits) {
        this.executionTimeUnits = executionTimeUnits;
    }

    public int getExecutedTimeUnits() {
        return executedTimeUnits;
    }

    public void setExecutedTimeUnits(int executedTimeUnits) {
        this.executedTimeUnits = executedTimeUnits;
    }
}
