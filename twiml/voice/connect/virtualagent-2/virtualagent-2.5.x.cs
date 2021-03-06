using System;
using Twilio.TwiML;
using Twilio.TwiML.Voice;


class Example
{
    static void Main()
    {
        var response = new VoiceResponse();
        response.Say("Hello! You will be now be connected to a virtual agent.");
        var connect = new Connect(action: new Uri("https://myactionurl.com/virtualagent_ended"));
        connect.VirtualAgent(connectorName: "project", statusCallback: "https://mycallbackurl.com");
        response.Append(connect);

        Console.WriteLine(response.ToString());
    }
}
