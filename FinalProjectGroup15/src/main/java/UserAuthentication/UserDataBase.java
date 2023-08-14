/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserAuthentication;

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
public class UserDataBase extends JFrame {

    public String filePath = "C:\\Users\\yaswa\\OneDrive\\Documents\\NetBeansProjects\\Database\\UserData\\Data.csv";

    public void RegFileWrite(String Name, String RollNumber, String Passw, String RePass, String PhoneNumber, String MailID, String SecurityQuestion, String SecurityAnswer) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(filePath));
        FileWriter f = new FileWriter(filePath, true);
        String finalPass = null;
        if (Passw.equals(RePass)) {
            finalPass = Passw;
            String data = Name + "," + RollNumber + "," + finalPass + ",'+91" + PhoneNumber + "'" + "," + "'" + MailID + "'"+ "," + SecurityQuestion+ "," + SecurityAnswer;
            boolean found = false;
            try {
                String line = null;

                while ((line = bfr.readLine()) != null) {
                    if (line.contains(RollNumber)) {
                        found = true;
                        break;
                     
                    }
                }

                if (!found) {
                    f.append(data);
                    f.append("\n");
                    f.close();
                    JOptionPane.showMessageDialog(null, "Registered Successfully");

                } else {
                    JOptionPane.showMessageDialog(null, "User already exists");

                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Unable to register");
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "The PassWords doesn't match");
        }

    }

    public String RegFileReadName(String RollNumber) throws FileNotFoundException, IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(filePath));

        String line = null;
        String linedataofname = null, linedataofpass = null, linedataofrollandpass = null, linedataofroll = null;

        while ((line = bfr.readLine()) != null) {
            if (line.contains(RollNumber)) {
                linedataofname = line.split(",")[0];
                linedataofrollandpass = line.split(",")[1];
                linedataofpass = line.split(",")[2];
                break;
            } else {
                continue;
            }
        }
//           System.out.println(linedataofpass);

        return linedataofname;

    }

    public String RegFileReadNum(String RollNumber) throws Exception {
        BufferedReader bfr = new BufferedReader(new FileReader(filePath));

        String line = null;
        String linedataofNum = null;

        while ((line = bfr.readLine()) != null) {
            if (line.contains(RollNumber)) {
                linedataofNum = line.split(",")[3];
                break;
            } else {
                continue;
            }
        }
        return linedataofNum;
    }
    public String getPass(String RollNumber) throws Exception {
        BufferedReader bfr = new BufferedReader(new FileReader(filePath));

        String line = null;
        String passw = null;

        while ((line = bfr.readLine()) != null) {
            if (line.contains(RollNumber)) {
                passw = line.split(",")[2];
                break;
            } else {
                continue;
            }
        }
        return passw;
    }

    public boolean checkRollExists(String RollNumber) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();

        String line;
        boolean found = false;
        while ((line = bfr.readLine()) != null) {
            if (line.contains(RollNumber)) {
                found = true;
                break;

            } else {
                found = false;
            }

        }

        if (found == false) {
            JOptionPane.showMessageDialog(rootPane, "User With the RollNumber does not exists");
        }
        bfr.close();

        return found;
    }

    public String getFilePath() {
        return filePath;
    }


}
