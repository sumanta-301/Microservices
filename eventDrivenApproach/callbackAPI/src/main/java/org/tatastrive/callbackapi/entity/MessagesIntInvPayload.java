package org.tatastrive.callbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagesIntInvPayload {
    private long engagementId;
    private String hollandcode;
    private int reverificationCode;

}
