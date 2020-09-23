package escalonador;
import java.util.Scanner;

public class Escalonador {
    public static void main(String[] args) {
        System.out.println("Salve"); 
        Scanner scan = new Scanner(System.in);
        Object zap = scan.next();
        System.out.println((int)zap);
        
        switch((int)zap){
            case 1:
                System.out.println("1");
            break;
            case 2:
                System.out.println("2");
            break;
            default:
                System.out.println("Deu cocô");
            break;
        }
    }
    
}
