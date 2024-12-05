package com.incubateur.carpoolconnect.dto;

import com.incubateur.carpoolconnect.entities.Route;
import com.incubateur.carpoolconnect.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private String body;

    private boolean isFlagged;

    private UserDto commenter;

    private RouteDto route;
}
