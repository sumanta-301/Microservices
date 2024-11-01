package org.tatastrive.callbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessagesForAddress {
    private String district;
    private int pincode;
    private long dbUserId;
}
