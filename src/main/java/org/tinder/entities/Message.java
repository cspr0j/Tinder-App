package org.tinder.entities;

import lombok.Data;
import org.tinder.utils.Converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;

@Data
public class Message {
    private Long userId;
    private Long targetId;
    private String text;
    private Timestamp date;

    public Message(Long userId, Long targetId, String text, Timestamp date) throws ParseException {
        this.userId = userId;
        this.targetId = targetId;
        this.text = text;
        this.date = date;
    }

    public Message(Long userId, Long targetId, String text) {
        this.userId = userId;
        this.targetId = targetId;
        this.text = text;
        this.date = Timestamp.from(Instant.now());
    }

    @Override
    public String toString() {
        return "Message{" +
                "userId=" + userId +
                ", targetId=" + targetId +
                ", text=" + text +
                ", date=" + Converter.converterToString(date) +
                '}';
    }
}
