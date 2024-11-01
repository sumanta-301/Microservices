package org.tatastrive.callbackapi.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class ExternalPartnerDto {

    private Long engagementId;
    private Long dbUserId;
    private String hollandcode;
    private Integer reverificationCode;
    private String pdfDownloadUrl;
    private String callbackUrl;
    @NotBlank
    private String messageId;
    private List<String> suggestedCourses;
    private String firstName;
    private String lastName;
    private String email;
    private String qualification;
    private String gender;
    private String dob;
    private String url;
    private Long pincode;
    private String district;

}


