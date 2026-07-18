package com.tfi.Econexo.utils.notification;

import com.tfi.Econexo.model.auth.UserSec;
import com.tfi.Econexo.model.notifications.Notification;
import com.tfi.Econexo.repository.notification.NotificationRepository;
import com.tfi.Econexo.service.auth.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserService userService;
    private final EmailService emailService;

    @Async
    public void notifyUser(String email, String message, String emailSubject){
        UserSec user = userService.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Notification not = new Notification();
        not.setUser(user);
        not.setMessage(message);
        notificationRepository.save(not);

        emailService.sendGenericNotification(email, emailSubject, message);
    }

    public long countUnreadNotifications(String email) {
        return notificationRepository.countByUser_EmailAndIsReadFalse(email);
    }

    public List<Notification> getNotifications(String email) {
        return notificationRepository.findByUser_EmailOrderByCreatedAtDesc(email);
    }

    public void markAllAsRead(String email) {
        notificationRepository.markAllAsReadByUserEmail(email);
    }

    public void deleteNotification(Long id, String email){
        Notification not = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found"));

        if(!not.getUser().getEmail().equals(email)){
            throw new SecurityException("Do not allowed to delete this message");
        }

        notificationRepository.delete(not);
    }

    @Transactional
    public void deleteAllNotifications(String email){
        notificationRepository.deleteByUser_Email(email);
    }
}
