package org.tatastrive.callbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tatastrive.callbackapi.messaging.MessageConsumer;
import org.tatastrive.callbackapi.utility.StringListConverter;

import javax.persistence.*;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parkingQueueArchive")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long dbUserId;
    private long engagementId;
    private String hollandcode;
    private int reverificationCode;
    private String pdfDownloadUrl;
    private String callbackUrl;
    private String updatedCallbackUrl;
    private String messageId;
    @Convert(converter = StringListConverter.class )
    private List<String> suggestedCourses;
    private String firstName;
    private String lastName;
    private String email;
    private String  qualification;
    private String gender;
    private String dob;
    private Long pincode;
    private String district;
    private String mobileNumber;
    private Boolean messageProcessedStatus;
    @Enumerated(EnumType.STRING)
    private MessageConsumer.PayloadType payloadType;


    @PrePersist
    private void prePersist() {
        if(this.updatedCallbackUrl == null) {
            this.updatedCallbackUrl = this.callbackUrl;
        }
        if(this.messageProcessedStatus == null){
            this.messageProcessedStatus =false;
        }

    }
}
