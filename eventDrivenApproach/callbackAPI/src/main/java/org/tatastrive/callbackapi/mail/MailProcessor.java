package org.tatastrive.callbackapi.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tatastrive.callbackapi.repositories.PartnerAuditRepository;

import java.math.BigInteger;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class MailProcessor {


    private final PartnerAuditRepository auditRepository;
    private final JavaMailSender mailSender;

    @Value("${mail.recipients}")
    private String recipients;

    @Scheduled(cron = "0 30 10 * * *") // Scheduled for 10:30 AM every day
    private void sendFirstMail() {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(19, 31)); // Previous day 7:31 PM
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 29)); // Today 10:29 AM
        sendRecordTransferredMail(startTime, endTime, "Karyapath Youth Data Transferred to Partners");
    }

    @Scheduled(cron = "0 30 14 * * *") // Scheduled for 2:30 PM every day
    private void sendSecondMail() {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(19, 30)); // previous day 7:30 PM
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 29)); // Today 2:29 PM
        sendRecordTransferredMail(startTime, endTime, "Karyapath Youth Data Transferred to Partners");
    }

    @Scheduled(cron = "0 30 19 * * *") // Scheduled for 7:30 PM every day
    private void sendThirdMail() {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(19, 30)); //  previous day 7:30
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 29)); // Today 7:29 PM
        sendRecordTransferredMail(startTime, endTime,"Karyapath Youth Data Transferred to Partners Daily Report");
    }

    @Scheduled(cron = "0 0 9 ? * MON") //scheduled for 9 AM every Monday
    private void sendRecordWeeklyMail() {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusWeeks(1).with(DayOfWeek.MONDAY), LocalTime.of(0, 0));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now().with(DayOfWeek.SUNDAY), LocalTime.of(23, 59, 59));
        sendRecordTransferredMail(startTime, endTime, "Karyapath Youth Record Transferred to Partners Weekly Report");
    }
    @Scheduled(cron = "0 0 9 1 * ?") //scheduled for 9 AM every Month
    private void sendRecordMonthlyMail(){
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()), LocalTime.MAX);
        sendRecordTransferredMail(startTime, endTime, "Karyapath Youth Record Transferred to Partners Monthly Report");
    }

    public void sendRecordTransferredMail(LocalDateTime startTime, LocalDateTime endTime,String mailSubject) {
        List<Object[]> countsOfRecordsOfEachUrl = auditRepository.countRecordsProcessedBetweenTimestamp(startTime, endTime);

        String subject = mailSubject;
//        StringBuilder bodyBuilder = new StringBuilder("Greetings!!\n\n");
//       if (countsOfRecordsOfEachUrl.isEmpty()) {bodyBuilder.append("Zero records transferred to partners!").append("\n\n").append("Regards").append("\n").append("TataSTRIVE");}
//       else {
//           for (Object[] record : countsOfRecordsOfEachUrl) {
//               String url = (String) record[0];
//               BigInteger count = (BigInteger) record[1];   // Assuming the second element is the count
//               bodyBuilder.append(count).append(" records have been sent to ").append(url).append("\n");
//           }
//       }
        StringBuilder bodyBuilder = new StringBuilder("Dear Partners,\n\n");

        if (countsOfRecordsOfEachUrl.isEmpty()) {
            bodyBuilder.append("This is to inform you that 0 records were transferred during this cycle.\n\n");
        } else {
            bodyBuilder.append("The following record transfer details are for your information:\n\n");
            for (Object[] record : countsOfRecordsOfEachUrl) {
                String url = (String) record[0];
                BigInteger count = (BigInteger) record[1];
                bodyBuilder.append("- ").append(count).append(" records were sent to ").append(url).append("\n");
            }
        }

        bodyBuilder.append("Thank you,\nTata STRIVE");


        String body = bodyBuilder.toString();

        SimpleMailMessage mailMsg = new SimpleMailMessage();
        mailMsg.setFrom("sumanta.ghosh@tatastrive.com");
        mailMsg.setTo(recipients.split(","));
        mailMsg.setSubject(subject);
        mailMsg.setSentDate(Date.valueOf(LocalDate.now()));
        mailMsg.setText(body);
        mailSender.send(mailMsg);
    }
    //@Bean
    public void sendRecordTransferFailMail(String subject, String body){
        SimpleMailMessage mailMsg = new SimpleMailMessage();
        mailMsg.setFrom("sumanta.ghosh@tatastrive.com");
        mailMsg.setSubject(subject);
        mailMsg.setText(body);
        mailMsg.setTo(recipients.split(","));
        mailMsg.setSentDate(Date.valueOf(LocalDate.now()));
        mailSender.send(mailMsg);
    }
}
