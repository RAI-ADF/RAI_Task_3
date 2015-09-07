
import java.io.BufferedReader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class ReadInput extends Thread{
    private BufferedReader inputStream;
    
    public ReadInput(BufferedReader inputStream){
        this.inputStream = inputStream;
    }
    
    @Override
    public void run(){
        try{
            String inputan;
            while((inputan = inputStream.readLine()) != null){
                System.out.println(inputan);
                System.out.print(">> ");
            }
        }catch(Exception e){}
    }
}
