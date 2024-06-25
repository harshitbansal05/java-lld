package sa.com.barraq.taskScheduler.csprocess;

import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import sa.com.barraq.taskScheduler.model.job.request.dto.ReceiverResponse;

import java.util.HashMap;
import java.util.Map;

public class ReceiverFactory {
    private final static Map<String, CSProcess> receivers = new HashMap<>();

    public static Receiver getReceiver(String channelId, AltingChannelInput<Object>[] in, ReceiverResponse out) {
        if (receivers.containsKey(channelId)) return (Receiver) receivers.get(channelId);
        receivers.put(channelId, new Receiver(in, out));
        return (Receiver) receivers.get(channelId);
    }

    public static ReceiverWithTimeout getReceiverWithTimeout(String channelId, AltingChannelInput<Object>[] in, ReceiverResponse out, long timeout) {
        if (receivers.containsKey(channelId)) return (ReceiverWithTimeout) receivers.get(channelId);
        receivers.put(channelId, new ReceiverWithTimeout(in, out, timeout));
        return (ReceiverWithTimeout) receivers.get(channelId);
    }
}
