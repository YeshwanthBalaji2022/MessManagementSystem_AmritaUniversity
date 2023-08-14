/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 *
 * @author yaswa
 */
public class trassh {
    public void sendOTP(String Name, String PhoneNumber, String OTP) throws Exception {
//        // This URL is used for sending messages
//

//        String myURI = "https://api.bulksms.com/v1/messages";
//
//        String PhNum = PhoneNumber.substring(1, PhoneNumber.length() - 1);
//        System.out.println(PhNum);
//
//        // change these values to match your own account
//        String myUsername = "yaswanthbalaji11";
//        String myPassword = "dmM5qF!ZX_t!PQx";
//        String value = "Hi "+Name+" , Your OTP is "+OTP;
//        
//
//        // the details of the message we want to send
//        String myData = "{to: \""+PhNum+"\", encoding: \"UNICODE\", body: \"Hi "+Name+" Your OTP is "+ OTP +"\"}";
////        String myData = String.format("{\"body\": \"Hi {F0#################}, Your OTP is \", \"to\": [{\"address\": \"+%s\",\"fields\": [\"%s\"]}]\" }",
////            PhNum, Name);
////        String myData = String.format("{\"body\": \"Hi {F0#################}, Your OTP is \", \"from\": \"+91123654789\", \"to\": [{\"address\": \"+%s\",\"fields\": [\"%s\"]}] }",
////    PhNum, Name);
////String myData = String.format("{\"body\": \" {F0##########################################################}\", \"to\": [{\"address\": \"+%s\",\"fields\": [\"%s\"]}] }",
////     PhNum, value);
//
//
//
//
//        System.out.println(myData);
//        // if your message does not contain unicode, the "encoding" is not required:
//        // String myData = "{to: \"1111111\", body: \"Hello Mr. Smith!\"}";
//
//        // build the request based on the supplied settings
//        URL url = new URL(myURI);
//        HttpURLConnection request = (HttpURLConnection) url.openConnection();
//        request.setDoOutput(true);
//
//        // supply the credentials
//        String authStr = myUsername + ":" + myPassword;
//        String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
//        request.setRequestProperty("Authorization", "Basic " + authEncoded);
//
//        // we want to use HTTP POST
//        request.setRequestMethod("POST");
//        request.setRequestProperty("Content-Type", "application/json");
//
//
//        // write the data to the request
//        OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
//        out.write(myData);
//        out.close();
//
//        // try ... catch to handle errors nicely
//        try {
//            // make the call to the API
//            InputStream response = request.getInputStream();
//            BufferedReader in = new BufferedReader(new InputStreamReader(response));
//            String replyText;
//            while ((replyText = in.readLine()) != null) {
//                System.out.println(replyText);
//            }
//            in.close();
//        } catch (IOException ex) {
//            System.out.println("An error occurred:" + ex.getMessage());
//            BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
//            // print the detail that comes with the error
//            String replyText;
//            while ((replyText = in.readLine()) != null) {
//                System.out.println(replyText);
//            }
//            in.close();
//        }
//        request.disconnect();

        String myURI = "https://api.bulksms.com/v1/messages";
        String PhNum = PhoneNumber.substring(1, PhoneNumber.length() - 1);
        System.out.println(PhNum);

        // change these values to match your own account
        String myUsername = "yaswanthbalaji11";
        String myPassword = "dmM5qF!ZX_t!PQx";

        // the details of the message we want to send
        String myData = "{to: \"" + PhNum + "\", encoding: \"UNICODE\", body: \"Hi " + Name + "!  OTP is " + OTP +" \"}";

        // if your message does not contain unicode, the "encoding" is not required:
        // String myData = "{to: \"1111111\", body: \"Hello Mr. Smith!\"}";
        // build the request based on the supplied settings
        URL url = new URL(myURI);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setDoOutput(true);

        // supply the credentials
        String authStr = myUsername + ":" + myPassword;
        String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
        request.setRequestProperty("Authorization", "Basic " + authEncoded);

        // we want to use HTTP POST
        request.setRequestMethod("POST");
        request.setRequestProperty("Content-Type", "application/json");

        // write the data to the request
        OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
        out.write(myData);
        out.close();

        // try ... catch to handle errors nicely
        try {
            // make the call to the API
            InputStream response = request.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(response));
            String replyText;
            while ((replyText = in.readLine()) != null) {
                System.out.println(replyText);
            }
            in.close();
        } catch (IOException ex) {
            System.out.println("An error occurred:" + ex.getMessage());
            BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
            // print the detail that comes with the error
            String replyText;
            while ((replyText = in.readLine()) != null) {
                System.out.println(replyText);
            }
            in.close();
        }
        request.disconnect();


    }
    
    //       public String sendOTP(String Name, String PhoneNumber) {
//        try {
//            Random randNum = new Random();
//            long OTTP = randNum.nextInt(80000) + 10000;
//            String OTP = Long.toString(OTTP);
//
//            // Construct data
//            String apiKey = "apikey=" + "MzMzNTM4NjIzOTQ0NTgzNzVhMzE1MzM4Nzg2NTQzNTA=	";
//            String message = "&message=" + URLEncoder.encode("Hi " + Name + "! Your OTP is " + OTP, "UTF-8");
//            String sender = "&sender=" + URLEncoder.encode("TXTLCL", "UTF-8");
//            String numbers = "&numbers=" + PhoneNumber;
//
//            // Send data
//            URL url = new URL("https://api.textlocal.in/send/?");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            String data = apiKey + numbers + message + sender;
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
//            conn.getOutputStream().write(data.getBytes("UTF-8"));
//
//            // Read the response
//            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = rd.readLine()) != null) {
//                response.append(line);
//            }
//            rd.close();
//
//            return OTP;
//        } catch (Exception e) {
//            System.out.println("Error sending SMS: " + e);
//            return "Error " + e;
//        }
//    }
    
    
    //    public void sendMail(String toMailID,String Message, String Subject){
//        
////          String host = "smtp.gmail.com"; // gmail variable
////          
////          Properties properties = System.getProperties(); //sys propeties
////          
////          System.out.println("PROPERTIES" + properties); 
////          
////          properties.put("mail.smtp.host", host); //host set
////          properties.put("mail.smtp.port", 465);
////          properties.put("mail.smtp.ssl", "true");
////          properties.put("mail.smtp.auth", "true");
////          
////          
////          
////          Session.getInstance(properties, new Authenticator(){
////              @Override
////              protected PasswordSuthentication getPasswordAuthentication(){
////                  
////                  
////                  
////                  return new PasswordAuthentication("");
////              }
////          });
////          
////        
//    }
//    public static void main(String[] args) throws Exception {
//        NotificationSender nss = new NotificationSender();
//
//        nss.sendOTP("Yeshwatt", "+9163044858577");
//    }
}
