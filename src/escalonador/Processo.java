package escalonador;

public class Processo {
    private final int id;
    private String state;
    private int arrival;
    private int executionTimeUnits;
    private int executedTimeUnits;
    private static int processosTerminados;
    private int remaningQuantumTime;

    public Processo(int id, int arrival, int executionTimeUnits) {
        this.id = id;
        this.arrival = arrival;
        this.executionTimeUnits = executionTimeUnits;
        this.executedTimeUnits = 0;
        
    }

    public int getRemaningQuantumTime() {
        return remaningQuantumTime;
    }

    public boolean terminou(){
        return executedTimeUnits >= executionTimeUnits;
    }
    
    public void setRemaningQuantumTime(int remaningQuantumTime) {
        this.remaningQuantumTime = remaningQuantumTime;
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public static int getProcessosTerminados() {
        return processosTerminados;
    }
    
    public static void setProcessosTerminados(int processosTerminados) {
        Processo.processosTerminados = processosTerminados;
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
        if(executedTimeUnits!=this.executedTimeUnits && executedTimeUnits== this.executionTimeUnits)
            processosTerminados++;
        this.executedTimeUnits = executedTimeUnits;
    }
    
    public int compareTo(Processo o){
        if(o.getArrival() == this.getArrival())
            return this.getId()-o.getId();
        else
            return this.arrival - o.getArrival();
    }
}
