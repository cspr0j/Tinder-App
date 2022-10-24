package org.tinder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.tinder.utils.Converter;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@AllArgsConstructor
public class Message {
    private Long messageId;
    private Long userId;
    private Long targetId;
    private String text;
    private Timestamp date;

    public Message(Long userId, Long targetId, String text) {
        this.userId = userId;
        this.targetId = targetId;
        this.text = text;
        this.date = Timestamp.from(Instant.now());
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", userId=" + userId +
                ", targetId=" + targetId +
                ", text=" + text +
                ", date=" + Converter.converterToString(date) +
                '}';
    }
}
