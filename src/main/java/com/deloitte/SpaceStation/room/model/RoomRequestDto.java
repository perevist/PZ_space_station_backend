package com.deloitte.SpaceStation.room.model;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class RoomRequestDto {
    @NotNull(message = "Name can not be blank")
    private String name;
    @NotNull(message = "Floor can not be blank")
    private int floor;
    @NotNull(message = "Number od worksites can not be blank")
    @Positive(message = "Number od worksites must be a positive number")
    private Long numberOfWorksites;
}
