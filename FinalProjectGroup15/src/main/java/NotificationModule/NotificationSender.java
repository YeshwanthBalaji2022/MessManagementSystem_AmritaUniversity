package NotificationModule;

import java.net.*;
import java.util.Base64;
import java.io.*;
import java.util.Random;

public class NotificationSender {

    public void sendConfirmationSMS(String Name, String PhoneNumber) throws Exception {
        String myURI = "https://api.bulksms.com/v1/messages";
        String PhNum = PhoneNumber.substring(1, PhoneNumber.length() - 1);
        System.out.println(PhNum);

        String myUsername = ""; //username of the bulksms account
        String myPassword = ""; //password of the bulksms account

        String myData = "{to: \"" + PhNum + "\", encoding: \"UNICODE\", body: \"Hi " + Name + "!  30 rupees deducted from your wallet and Your entry was made. Contact Admin for any queries\"}";

        URL url = new URL(myURI);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setDoOutput(true);

        String authStr = myUsername + ":" + myPassword;
        String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
        request.setRequestProperty("Authorization", "Basic " + authEncoded);

        request.setRequestMethod("POST");
        request.setRequestProperty("Content-Type", "application/json");

        OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
        out.write(myData);
        out.close();

        try {
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
    
    
}
