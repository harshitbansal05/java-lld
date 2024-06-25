package sa.com.barraq.taskScheduler.csprocess;

import org.jcsp.lang.AltingChannelOutput;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;

import java.util.HashMap;
import java.util.Map;

public class SenderFactory {

    private final static Map<String, CSProcess> senders = new HashMap<>();

    public static Sender getSender(String channelId, ChannelOutput<Object> out, Object data) {
        if (senders.containsKey(channelId)) return (Sender) senders.get(channelId);
        senders.put(channelId, new Sender(out, data));
        return (Sender) senders.get(channelId);
    }

    public static SenderWithTimeout getSenderWithTimeout(String channelId, AltingChannelOutput<Object> out, Object data, long timeout) {
        if (senders.containsKey(channelId)) return (SenderWithTimeout) senders.get(channelId);
        senders.put(channelId, new SenderWithTimeout(out, data, timeout));
        return (SenderWithTimeout) senders.get(channelId);
    }

    public static Poisoner getPoisoner(String channelId, ChannelOutput<Object> out, int poison) {
        if (senders.containsKey(channelId)) return (Poisoner) senders.get(channelId);
        senders.put(channelId, new Poisoner(out, poison));
        return (Poisoner) senders.get(channelId);
    }
}
