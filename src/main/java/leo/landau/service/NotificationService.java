package leo.landau.service;

import java.util.List;
import javax.transaction.Transactional;

import jakarta.inject.Singleton;
import leo.landau.model.Notification;
import leo.landau.repository.NotificationRepository;

@Singleton
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification sendNotification(Long userId, String message) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

}
