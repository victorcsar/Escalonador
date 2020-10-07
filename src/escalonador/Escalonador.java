package escalonador;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Escalonador {
            
    public static void main(String[] args) {
        Processador processor = Processador.getInstance();
        ArrayList<LinkedList<Processo>> listsOFprocessos = new ArrayList<>();
        Processo[] processos;
        listsOFprocessos.add(new LinkedList<>());
        Random random = new Random();
        Processo.setProcessosTerminados(0);
        
        //Criação dos Processos.
        int nProcessos = 2+random.nextInt(18);
        System.out.println("Foram criados "+nProcessos+" Processos:");
        processos = new Processo[nProcessos];
        for(int i = 0; i<nProcessos; i++){
            Processo p = new Processo(i+1, random.nextInt(19), 1+ random.nextInt(10));
            p.setState("Pronto");
            processos[i]=p;
            System.out.println("    -id:  "+p.idToString()+  
                    "   Tempo de Chegada:   "+ p.getArrival()+(p.getArrival()>9?"":" ")+
                    "    Tempo de Execução   "+p.getExecutionTimeUnits()+
                    (p.getExecutionTimeUnits()>9?"":" "));
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
        
        System.out.println("\n-------------Organização temporal dos processos-------------");
        for(Processo p: processos){
            System.out.print(p.idToString()+" ");
        }
        System.out.println("\n-------------Organização temporal dos processos-------------\n");
        
        new Thread(){
            @Override
            public void run(){
                int idProcesso=0;
                processor.start(processor);
                while((idProcesso<nProcessos)&&(processor.getTime()<=processos[nProcessos-1].getArrival())){
                    try {
                        if(processos[idProcesso].getArrival() == processor.getTime()){
                            do{
                                System.out.println("O Processo: "+processos[idProcesso]
                                        .idToString()+" foi  adicionado  à  fila 1 no Tempo: "+processor.getTime());
                                listsOFprocessos.get(0).add(processos[idProcesso]);
                                idProcesso++;
                            }while(idProcesso<nProcessos&&processos[idProcesso].getArrival()==processor.getTime());
                        }
                        Thread.sleep(200);

                    } catch (InterruptedException ex) {
                        System.out.println("Adicionando processos"+ex);
                    }
                }
                System.out.println("-----------------------------------------------------");
                System.out.println("     Todos os processos foram adicionados à fila.");
                System.out.println("-----------------------------------------------------");
                this.stop();
            }
        }.start();
        //Chamada dos processos no tempo certo.
         
       
       while(nProcessos!=Processo.getProcessosTerminados()){
            try{
                for(int i = 0; i<listsOFprocessos.size();i++){
                    boolean preemption = false;
                    LinkedList<Processo> l = listsOFprocessos.get(i);
                    while(!l.isEmpty()&&!preemption){
                        Processo p = l.getFirst();
                        System.out.println("Fila " + (i+1) +" Quantum de " + (i+1)+" Segundos.");
                        processor.execute(p, (i+1));
                        do{
                            if(i>0&&!listsOFprocessos.get(0).isEmpty()){
                                Processador.getInstance().interromper();
                                preemption = true;
                            }
                            Thread.sleep(200);
                        }while(Processador.executando()&&!preemption);
                        if(preemption)
                            i=-1;
                        else if(!p.terminou()){
                            l.removeFirst();
                            if (i<listsOFprocessos.size()-1)
                                listsOFprocessos.get(i+1).add(p);
                            else if(i == listsOFprocessos.size()-1){
                                LinkedList<Processo> l2 = new LinkedList<>();
                                l2.add(p);
                                listsOFprocessos.add(l2);
                            }
                        }
                        else{
                            l.removeFirst();
                            System.out.println("O Processo: "+p.idToString()+" está saindo da estrutura no Tempo: "+ Processador.getInstance().getTime());
                        }
                    }
                }
            
           }catch(NoSuchElementException ex){
           } catch (InterruptedException ex) {
                Logger.getLogger(Escalonador.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       processor.stopClock();
    }
}
