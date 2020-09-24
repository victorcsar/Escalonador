package escalonador;

public class Processo {
    private final int id;
    private int arrival;
    private int executionTimeUnits;
    private int executedTimeUnits;

    public Processo(int id, int arrival, int executionTimeUnits) {
        this.id = id;
        this.arrival = arrival;
        this.executionTimeUnits = executionTimeUnits;
    }

    public int getId() {
        return id;
    }
    
    public String idToString(){
        return "P"+this.getId()+(this.getId()>9?"":" ");
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
    
    public int compareTo(Processo o){
        if(o.getArrival() == this.getArrival())
            return this.getId()-o.getId();
        else
            return this.arrival - o.getArrival();
    }
    
    
}
