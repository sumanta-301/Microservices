package org.tatastrive.callbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessagesForBasicBeneficiary {
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private long dbUserId;
    private String qualification;
    private String mobileNumber;
}
