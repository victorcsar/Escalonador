package escalonador;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Processador extends Thread{
    private int time;
    private Thread clock;
    private Thread executar;
    private static boolean executando;
    private static Processador processador;

    public static synchronized Processador getInstance(){
        if(processador == null)
            processador = new Processador();
        return processador;
    }
    
    public static boolean executando(){
        return executando;
    }
    
    private Processador() {
        this.time = 0;
        executando = false;
    }

    public int getTime() {
        return time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }

    public void interromper(){
        processador.executar.interrupt();
    }
    
    public void execute(Processo p, int timeUnits) {
        executar = new Thread(){
            @Override
            public void run(){
                boolean quantumAjust = p.getState().equals("Suspenso");
                p.setState("Executando");
                Processador.executando = true;
                int initialTime = time;
                System.out.println("O Processo: "+p.idToString()+" está iniciando  execução no Tempo: "+ initialTime);
                try {
                    if(quantumAjust){
                        while(p.getRemaningQuantumTime()>time-initialTime){
                            Thread.sleep(200);
                        }
                    }
                    else
                        while((initialTime+timeUnits>time)&&(p.getExecutedTimeUnits()+(time-initialTime)<p.getExecutionTimeUnits())){
                            Thread.sleep(200);
                        }
                } catch (InterruptedException ex) {
                    System.out.println("O Processo: "+p.idToString()+" teve  execução interrompida no Tempo: "+ time);
                    Processador.executando = false;
                    System.out.println("O Processo: "+p.idToString()+" ExecutedTimeUnits before: "+ p.getExecutedTimeUnits());
                    p.setExecutedTimeUnits(p.getExecutedTimeUnits()+(time-initialTime));
                    System.out.println("O Processo: "+p.idToString()+" ExecutedTimeUnits after: "+ p.getExecutedTimeUnits());
                    p.setRemaningQuantumTime(timeUnits+initialTime-time);
                    p.setState("Suspenso");
                    this.stop();
                }
                p.setExecutedTimeUnits(p.getExecutedTimeUnits()+(time-initialTime));
                System.out.println("O Processo: "+p.idToString()+" está terminando execução no Tempo: "+time+"  -  "+p.getExecutedTimeUnits()+" Unidades de tempo executadas."); 
                Processador.executando = false;
            }
        };
        executar.start();
    }

    public void start(Processador p) {
        clock = new Thread(){
            @Override
            public void run(){
                while(true){
                    try {
                        Thread.sleep(1000);
                        p.setTime(p.getTime()+1);
                    } catch (InterruptedException ex) {
                        System.out.println("Processador parou");
                    }
                }
            }
        };
        clock.start();
    }
    
    public void stopClock(){
        System.out.println("CPU desligada.");
        this.clock.stop();
    }
}
