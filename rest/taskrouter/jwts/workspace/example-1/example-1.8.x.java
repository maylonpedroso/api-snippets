// Install the Java helper library from twilio.com/docs/java/install
import java.util.List;
import java.util.ArrayList;

import com.twilio.http.HttpMethod;
import com.twilio.jwt.taskrouter.Policy;
import com.twilio.jwt.taskrouter.TaskRouterCapability;
import com.twilio.jwt.taskrouter.UrlUtils;

public class Example {
  // Get your Account SID and Auth Token from https://twilio.com/console
  // To set up environment variables, see http://twil.io/secure
  private static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
  private static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
  private static final String WORKSPACE_SID = "WSXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

  public static void main(String[] args) {
    Policy allowFetchSubresources = new Policy.Builder()
        .url(UrlUtils.workspace(WORKSPACE_SID) + "/**")
        .build();

    Policy allowUpdatesSubresources = new Policy.Builder()
        .url(UrlUtils.workspace(WORKSPACE_SID) + "/**")
        .method(HttpMethod.POST)
        .build();

    Policy allowDeleteSubresources = new Policy.Builder()
        .url(UrlUtils.workspace(WORKSPACE_SID) + "/**")
        .method(HttpMethod.DELETE)
        .build();


    List<Policy> policies = Arrays.asList(allowFetchSubresources, allowUpdatesSubresources,
        allowDeleteSubresources);


    TaskRouterCapability.Builder capabilityBuilder =
        new TaskRouterCapability.Builder(ACCOUNT_SID, AUTH_TOKEN, WORKSPACE_SID, WORKSPACE_SID)
            .policies(policies);

    String token = capabilityBuilder.build().toJwt();

    System.out.println(token);

    // By default, tokens are good for one hour.
    // Override this default timeout by specifiying a new value (in seconds).
    // For example, to generate a token good for 8 hours:
    token = capabilityBuilder.ttl(28800).build().toJwt();

    System.out.println(token);
  }
}
