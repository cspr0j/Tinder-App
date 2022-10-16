package org.tinder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private Long userId;
    private Long targetId;
    private StringBuilder text;
}
