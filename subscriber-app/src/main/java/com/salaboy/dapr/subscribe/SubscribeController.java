package com.salaboy.dapr.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SubscribeController {

    private static final Logger log = LoggerFactory.getLogger(SubscribeController.class);

    private List<String> notifications = new ArrayList<String>();

    @PostMapping("/notifications")
    public void receiveNotifications(@RequestBody Notification notification ) {
        log.info("Message Received: {}", notification.data());
        notifications.add(notification.data());
    }

    @GetMapping("/notifications")
    public List<String> getNotifications() {
        return notifications;
    }

}
