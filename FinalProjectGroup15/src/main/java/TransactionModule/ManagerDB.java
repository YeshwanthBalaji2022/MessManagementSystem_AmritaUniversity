/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransactionModule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ManagerDB {
    
    
   public String filePath = "C:\\Users\\yaswa\\OneDrive\\Documents\\NetBeansProjects\\Database\\ManagerData\\Data.csv";
   
   public void creditMoney(long amount) throws FileNotFoundException, IOException{
       BufferedReader bfr = new BufferedReader(new FileReader(filePath));
       StringBuilder content = new StringBuilder();
       
       String  line;
       
       while((line=bfr.readLine())!=null){
           if(line.contains("Balance")){
               String parts [] = line.split(",");
               String Finalamout = String.valueOf(amount+Long.parseLong(parts[1]));
               parts[1] = String.valueOf(Finalamout);
               line = String.join(",", parts);
           }
        content.append(line).append("\n");       
       }
       
       bfr.close();

    FileWriter f = new FileWriter(filePath);
    f.write(content.toString());
    f.close();
    
   }

}
