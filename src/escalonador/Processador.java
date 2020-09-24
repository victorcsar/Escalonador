package escalonador;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Processador {
    private int time;
    private Thread clock;

    public Processador() {
        this.time = 0;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }    
    
    public void execute(Processo p, int timeUnits) {
            int initialTime = time;
            System.out.println("O Processo: "+p.idToString()+" está iniciando  execução no Tempo: "+initialTime);
            while(initialTime+timeUnits>this.time){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Processador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("O Processo: "+p.idToString()+" está terminando execução no Tempo: "+time);   
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
    
    public void stop(){
        this.clock.stop();
    }
}
