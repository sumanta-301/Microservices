package org.tatastrive.callbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="partner_audit_table")
public class PartnerAuditTable {
    @Id
    private String messageId;
    @Column(columnDefinition = "TEXT")
    private String message;
    private LocalDateTime messageGenerateTimestamp;
    private LocalDateTime messageReceivedTimestamp;
    private LocalDateTime messageProcessedTimestamp;
    private long engagementId;
    private long dbUserId;
    private String partnerUrl;
    private String responseStatus;
}
