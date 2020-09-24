package escalonador;
public class Processador {

    public Processador() {
    }
    
    public void execute(Processo p, int timeUnits) {
        try{
            System.out.println("O Processo: "+p.idToString()+" está iniciando execução ");
            Thread.sleep(timeUnits*1000);
            System.out.println("O Processo: "+p.idToString()+" está terminando execução ");
        }catch(InterruptedException ex ){
            System.out.println("Processor"+ex);
        }
        
        
    }
}
