package leo.landau.controller;

import java.util.List;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import leo.landau.model.Notification;
import leo.landau.service.NotificationService;

@Tag(name = "notification")
@Controller("/notification")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class NotificationController {

    @Inject
    private NotificationService notificationService;

    @Post
    public HttpResponse<Notification> sendNotification(@QueryValue Long userId, @QueryValue String message) {
        return HttpResponse.ok(notificationService.sendNotification(userId, message));
    }

    @Get("/{userId}")
    public HttpResponse<List<Notification>> getNotifications(@PathVariable Long userId) {
        return HttpResponse.ok(notificationService.getNotifications(userId));
    }
}
