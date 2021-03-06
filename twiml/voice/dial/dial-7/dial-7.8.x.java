import com.twilio.twiml.voice.Dial;
import com.twilio.twiml.voice.Number;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.TwiMLException;


public class Example {
    public static void main(String[] args) {
        Number number = new Number.Builder("+15551239876").build();
        Dial dial = new Dial.Builder().record(Dial.Record.RECORD_FROM_ANSWER)
            .recordingTrack(Dial.RecordingTrack.INBOUND)
            .recordingStatusCallback("https://www.myexample.com/recording-handler").number(number).build();
        VoiceResponse response = new VoiceResponse.Builder().dial(dial).build();

        try {
            System.out.println(response.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}
