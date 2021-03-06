// Install the Java helper library from twilio.com/docs/java/install
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Notification;

import java.time.LocalDate;

public class Example {
  // Get your Account SID and Auth Token from https://twilio.com/console
  // To set up environment variables, see http://twil.io/secure
  public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
  public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    LocalDate lower = LocalDate.of(2009, 7, 6);
    LocalDate upper = LocalDate.of(2009, 7, 8);

    ResourceSet<Notification> notifications = Notification.reader()
        .setMessageDateAfter(lower)
        .setMessageDateBefore(upper)
        .setLog(1)
        .read();

    // Loop over notifications and print out a property for each one.
    for (Notification notification : notifications) {
      System.out.println(notification.getRequestUrl());
    }
  }
}
