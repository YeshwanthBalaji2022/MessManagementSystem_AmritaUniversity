/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransactionModule;

import UserAuthentication.UserDataBase;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author yaswa
 */
public class UserWallet extends JFrame {

    UserDataBase udb = new UserDataBase();
    ManagerDB mdb = new ManagerDB();

    public String filePath = "C:\\Users\\yaswa\\OneDrive\\Documents\\NetBeansProjects\\Database\\UserData\\Data.csv";

    public void addMoney(String RollNumber, int amount) throws FileNotFoundException, IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();

        String line, FinalAmount;
        String addData = "NO";
        while ((line = bfr.readLine()) != null) {
            if (line.contains(RollNumber)) {
                String parts[] = line.split(",");
                addData = "yes";

                if (parts.length < 8) {
                    line = line + "," + amount;
                } else if (parts.length >= 8) {
                    FinalAmount = String.valueOf(Integer.parseInt(parts[7]) + amount);
                    parts[7] = String.valueOf(FinalAmount);
                    line = String.join(",", parts);
                }

            }

            content.append(line).append("\n");
        }

        if (addData.equals("yes")) {
            String dispData = "Money has been credited to your wallet";
            JOptionPane.showMessageDialog(rootPane, dispData);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Unable to add amount(User doesn't exists)");
        }
        bfr.close();

        FileWriter f = new FileWriter(filePath);
        f.write(content.toString());
        f.close();
    }

    public void debitMoney(String RollNumber, long theamount) throws Exception {
        BufferedReader bfr = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();

        String line;

        while ((line = bfr.readLine()) != null) {
            if (line.contains(RollNumber)) {
                String parts[] = line.split(",");
                String zero = String.valueOf(0);
                if (parts.length < 8) {
                    if (parts[7].equals(zero)) {
                        JOptionPane.showMessageDialog(rootPane, "You dont have enough money to debit, Please add funds");
                    }
                } else {
                    String FinalAmount = String.valueOf(Integer.parseInt(parts[7]) - theamount);

                    parts[7] = String.valueOf(FinalAmount);
                    line = String.join(",", parts);
                    mdb.creditMoney(theamount);

                }
            }
            content.append(line).append("\n");
        }
        bfr.close();

        FileWriter f = new FileWriter(filePath);
        f.write(content.toString());
        f.close();
    }

//    public static void main(String[] args) throws IOException, Exception {
//        
//        UserWallet uw = new UserWallet();
////        uw.addMoney("1552",50);
//        uw.addMoney("1552", 100);
//        uw.debitMoney("1552", 5);
//    }
}
