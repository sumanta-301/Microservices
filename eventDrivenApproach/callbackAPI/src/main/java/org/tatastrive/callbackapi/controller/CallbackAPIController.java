//package org.tatastrive.callbackapi.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.tatastrive.callbackapi.entity.Messages;
//import org.tatastrive.callbackapi.services.impl.CallbackAPIServiceImpl;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/callback/student")
////@CrossOrigin(origins = {"*"})
//public class CallbackAPIController {
//    private final CallbackAPIServiceImpl studentServiceImpl;
//
//    public CallbackAPIController(CallbackAPIServiceImpl studentServiceImpl) {
//        this.studentServiceImpl = studentServiceImpl;
//    }
//    @PostMapping()
//    public ResponseEntity<Messages> saveStudentDetails(@Valid @RequestBody  Messages callback){
//       return ResponseEntity.status(HttpStatus.CREATED).body(studentServiceImpl.createStudent(callback));
//    }
//    @GetMapping("/{studentEngagementId}")
//    public ResponseEntity<Messages> getStudentDetailsByEngagementId(@PathVariable long studentEngagementId) {
//        return ResponseEntity.status(HttpStatus.OK).body(studentServiceImpl.findByStudentEngagementId(studentEngagementId));
//    }
//}
