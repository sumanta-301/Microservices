package org.tatastrive.callbackapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tatastrive.callbackapi.entity.PartnerAuditTable;

import java.time.LocalDateTime;
import java.util.List;

public interface PartnerAuditRepository extends JpaRepository<PartnerAuditTable, String> {
//    @Query("SELECT p FROM PartnerAuditTable p WHERE p.messageId = :messageId")
    //Optional<PartnerAuditTable> findByMessageId(String messageId);


//    @Query("SELECT p FROM PartnerAuditTable p WHERE p.messageId = '9f77a54d-b508-4065-af59-a0d371b1dabc'")
//    Optional<PartnerAuditTable> findByMessageId();
@Query(value = "SELECT partner_url, COUNT(*) FROM partner_audit_table " +
        "WHERE message_processed_timestamp BETWEEN ?1 AND ?2 " +
        "GROUP BY partner_url", nativeQuery = true)
List<Object[]> countRecordsProcessedBetweenTimestamp(LocalDateTime startTime, LocalDateTime endTime);
}
