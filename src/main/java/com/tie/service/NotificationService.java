package com.tie.service;

import com.tie.model.dao.Message;
import io.github.jav.exposerversdk.ExpoPushMessage;
import io.github.jav.exposerversdk.PushClient;
import io.github.jav.exposerversdk.PushClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    public static ExpoPushMessage createExpoMessage(String token, String senderName) {
        ExpoPushMessage expoPushMessage = new ExpoPushMessage();
        expoPushMessage.getTo().add(token);
        expoPushMessage.setTitle("Smatch");
        expoPushMessage.setBody(senderName + " sent you a new message!");
        return expoPushMessage;
    }

    public static ExpoPushMessage createPushMessage(String token, String senderName, Message message) {
        ExpoPushMessage expoPushMessage = createExpoMessage(token, senderName);
        Map<String, Object> data = new HashMap<>();
        data.put("messageId",message.getMessageId().getUniqueId());
        data.put("groupId", message.getMessageId().getGroupId());
        data.put("otherUserId", message.getMessageId().getSenderId());
        data.put("message", message.getMessage());
        data.put("type", "chat");
        expoPushMessage.setData(data);
        return expoPushMessage;
    }

    public static ExpoPushMessage push(ExpoPushMessage expoPushMessage) throws PushClientException, InterruptedException {
        String token = expoPushMessage.getTo().get(0);
        if (token == null) // in case a user is not registered to the push notification service
            return new ExpoPushMessage();

        if (!PushClient.isExponentPushToken(token))
            throw new Error("Token:" + token + " is not a valid token.");

        List<ExpoPushMessage> expoPushMessages = new ArrayList<>();
        expoPushMessages.add(expoPushMessage);

        PushClient client = new PushClient();
        List<List<ExpoPushMessage>> chunks = client.chunkPushNotifications(expoPushMessages);

        chunks.forEach(client::sendPushNotificationsAsync);

        return expoPushMessage;
    }
}
