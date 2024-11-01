package org.tatastrive.callbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MessagesForDocument {
private long engagementId;
private String pdfDownloadUrl;
private List<String> suggestedCourses;
private int reverificationCode;
private String hollandcode;
}
