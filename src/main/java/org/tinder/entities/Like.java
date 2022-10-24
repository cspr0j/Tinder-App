package org.tinder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Like {
    private Long userId;
    private Long likedUserId;
}