package sa.com.barraq.taskScheduler.model.job.request.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverResponse {
    private int receivedChIndex;
    private Object data;
}
