package escalonador;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Escalonador {
            
    public static void main(String[] args) {
        Processador processor = new Processador();
        LinkedList<LinkedList<Processo>> listsOFprocessos = new LinkedList<>();
        Processo[] processos;
        LinkedList<Processo> fila1 = new LinkedList<>();
        listsOFprocessos.add(fila1);
        Random random = new Random();
        
        //Criação dos Processos.
        int nProcessos = 1+random.nextInt(19);
        System.out.println("Foram criados "+nProcessos+" Processos:");
        processos = new Processo[nProcessos];
        for(int i = 0; i<nProcessos; i++){
            Processo p = new Processo("P"+(i+1), random.nextInt(19), 1+ random.nextInt(10));
            processos[i]=p;
            System.out.println("    -id: "+ p.getId()+"  Tempo de Chegada: "+p.getArrival()+"    Tempo de Execução   "+p.getExecutionTimeUnits());
        }
        //Criação dos Processos.
        
        //Chamada dos processos no tempo certo.
        for(int i=0;i<nProcessos;i++){
            for(int j=0;j<nProcessos;j++){
                if(((Processo)processos[j]).compareTo((Processo)processos[i])>0){
                    Processo temp = processos[i];
                    processos[i] = processos[j];
                    processos[j]=temp;
                }
            }
        }
        
        
        /*
        Thread t = new Thread(){
            @Override
            public void run(){
                for(Processo p: processos){
                        Thread.sleep(p.getArrival()*1000);
                        fila1.add(p);
                }
            }

        }
        t.start();
        */
        
        
        for(Processo p: processos){
            processor.execute(p, p.getExecutionTimeUnits());
        }
    }
}
