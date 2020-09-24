package escalonador;
public class Processador {

    public Processador() {
    }
    
    public void execute(Processo p, int timeUnits) {
        try{
            System.out.print(p.getId()+" - ");
            //Thread t = new Thread();
            Thread.sleep(timeUnits*1000);
        }catch(InterruptedException e ){
            System.out.println("Deu ruim");
        }
        
        
    }
}
