package org.tinder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private int userId;
    private int targetId;
    private StringBuilder text;
}
