/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransactionModule;

import TransactionModule.UserWallet;
import NotificationModule.NotificationSender;
import UserAuthentication.UserDataBase;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author yaswa
 */
public class TransactionModule {
    
        public String filePath = "C:\\Users\\yaswa\\OneDrive\\Documents\\NetBeansProjects\\Database\\EntryData\\Data.csv";
        
        UserDataBase udb = new UserDataBase();
        UserWallet uw = new UserWallet();
        NotificationSender ns = new NotificationSender();
    
    public void makeEntry(String RollNumber) throws IOException, Exception{
        
            boolean exist = udb.checkRollExists(RollNumber);
            
            if (exist){
                
                String phnNum = udb.RegFileReadNum(RollNumber);
                String name = udb.RegFileReadName(RollNumber);
                
            
            try (FileWriter f = new FileWriter(filePath,true)) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd , HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                String entryData = RollNumber + "," + formattedDateTime;
                
                f.append(entryData);
                f.append("\n");
                uw.debitMoney(RollNumber, 30);  //default amount of 30 rupees is deducted. It can be changed only by the developer as of now.
                ns.sendConfirmationSMS(name, phnNum); 
                JOptionPane.showMessageDialog(null, "Entry Made");
                
            }
            
            }
            
            else{
                JOptionPane.showMessageDialog(null,"Unable to make entry, Contact Admin");
        
    }
    
   
    
//    public static void main(String[] args) throws IOException {
//        TransactionModule tdm = new TransactionModule();
//        
//        tdm.makeEntry("CB.EN.U4AIE22156");
//    }
    
}
    
    
}