package escalonador;
import java.util.LinkedList;
import java.util.NoSuchElementException;
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
        int nProcessos = 2+random.nextInt(18);
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
                                        .idToString()+" foi  adicionado  à  fila no Tempo: "+processor.getTime());
                                fila1.add(processos[idProcesso]);
                                idProcesso++;
                            }while(idProcesso<nProcessos&&processos[idProcesso].getArrival()==processor.getTime());
                        }
                        Thread.sleep(200);

                    } catch (InterruptedException ex) {
                        System.out.println("Adicionando processos"+ex);
                    }
                }
                System.out.println("-----------------------------------------------------");
                System.out.println("     Todos os processos foram adicionados à fila");
                System.out.println("-----------------------------------------------------");
                executando[0]=false;
                this.stop();
            }
        }.start();
        //Chamada dos processos no tempo certo.
        
        
        
        
        
        
        
       
       while(executando[0]||(!fila1.isEmpty())){
           try{
               Processo p = (Processo)fila1.removeFirst();
               //System.out.println("Foi o "+p.idToString());
               processor.execute(p, p.getExecutionTimeUnits());
           }catch(NoSuchElementException ex){
           }
       }
       processor.stop();
       System.out.println("CPU deligada.");
    }
}
