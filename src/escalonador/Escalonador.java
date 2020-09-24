package escalonador;
import java.util.LinkedList;
import java.util.Random;

public class Escalonador {
            
    public static void main(String[] args) {
        Processador processor = new Processador();
        LinkedList<LinkedList<Processo>> listsOFprocessos = new LinkedList<>();
        Processo[] processos;
        LinkedList<Processo> fila1 = new LinkedList<>();
        listsOFprocessos.add(fila1);
        Random random = new Random();
        final Boolean[] executando = new Boolean[1];
        executando[0] = true;
        
        //Criação dos Processos.
        int nProcessos = 2+random.nextInt(19);
        System.out.println("Foram criados "+nProcessos+" Processos:");
        processos = new Processo[nProcessos];
        for(int i = 0; i<nProcessos; i++){
            Processo p = new Processo(i+1, random.nextInt(19), 1+ random.nextInt(10));
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
        
        System.out.println("-------------Array-----------");
        for(Processo p: processos){
            System.out.print(p.idToString()+" ");
        }
        System.out.println("\n-------------Array-----------");
        
        System.out.println("\n \n");
        new Thread(){
            int time = 0;
            @Override
            public void run(){
                int time = 0;
                int idProcesso=0;
                for(int i = 0; i<=processos[nProcessos-1].getArrival();i++){
                    try {
                        if(processos[idProcesso].getArrival() == time){
                            do{
                                fila1.add(processos[idProcesso]);
                                System.out.println("O Processo: "+processos[idProcesso]
                                        .idToString()+" foi adicionado na fila no Tempo:"+time);
                                idProcesso++;
                            }while(idProcesso<nProcessos&&processos[idProcesso].getArrival()==time);
                        }
                        Thread.sleep(1000);
                        time++;
                    } catch (InterruptedException ex) {
                        System.out.println("Adicionando processos"+ex);
                    }
                }
                System.out.println("-------------------------------------------");
                System.out.println("Todos os processos foram adicionados à fila");
                System.out.println("-------------------------------------------");
                executando[0]=false;
            }
        }.start();
        //Chamada dos processos no tempo certo.
        
        
        
        
        
        
        
       
       while(executando[0]||(!fila1.isEmpty())){
            if(!fila1.isEmpty()){
                Processo p = (Processo) fila1.removeFirst();
                processor.execute(p, p.getExecutionTimeUnits());
           }
           
       }
    }
}
