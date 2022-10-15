package org.tinder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Like {
    private int userId;
    private int likedUserId;
}
