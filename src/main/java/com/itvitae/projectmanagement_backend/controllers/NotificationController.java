//package com.itvitae.projectmanagement_backend.controllers;
//
//import com.itvitae.projectmanagement_backend.dto.notification.NotificationSummaryDTO;
//import com.itvitae.projectmanagement_backend.models.Notification;
//import com.itvitae.projectmanagement_backend.models.User;
//import com.itvitae.projectmanagement_backend.repositories.NotificationRepository;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/notifications")
//public class NotificationController {
//
//    private final NotificationRepository notificationRepository;
//
//    public NotificationController(NotificationRepository notificationRepository) {
//        this.notificationRepository = notificationRepository;
//    }
//
//    @GetMapping
//    public List<NotificationSummaryDTO> getUserNotifications(@AuthenticationPrincipal User currentUser) {
//        return notificationRepository.findByRecipientOrderByCreatedAtDesc(currentUser)
//                .stream()
//                .map(n -> new Notification(n.getMessage(), n.isRead(), n,getCreatedAt()))
//                .collect(Collectors.toList());
//    }
//}
