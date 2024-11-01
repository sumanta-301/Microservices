//package org.tatastrive.callbackapi.services.impl;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.tatastrive.callbackapi.entity.Messages;
////import org.tatastrive.callbackapi.repositories.CallbackAPIRepository;
//import org.tatastrive.callbackapi.services.CallbackAPIService;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//
//@Service
//@Slf4j
//public class CallbackAPIServiceImpl implements CallbackAPIService {
//  //  private final CallbackAPIRepository callbackAPIRepository;
//
//
//
//    @Override
//    public Messages createStudent(Messages callback) {
//        return callbackAPIRepository.save(callback);
//    }
//
//   public List<Messages> getAllStudent() {
//       return callbackAPIRepository.findAll();
//    }
//    public Messages findById(long id) {
//        return callbackAPIRepository.findById(id).orElse(null);
//    }
//    public Messages findByStudentEngagementId(long studentEngagementId) {
//        Messages callbackByEngagementId = callbackAPIRepository.findStudentByStudentEngagementId(studentEngagementId);
//        if(callbackByEngagementId ==null) {
//            log.error("Student Not Found with the engagementId{}", studentEngagementId);
//            throw new EntityNotFoundException("Student Not Found with the given engagementId: "+studentEngagementId);
//
//        }else {
//            return callbackByEngagementId;
//        }
//
//    }
//
//
//
//}
