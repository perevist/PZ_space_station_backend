package com.deloitte.SpaceStation.room.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class RoomRequestDto {
    @NotNull(message = "Name can not be blank")
    private String name;
    @NotNull(message = "Floor can not be blank")
    private Long floor;
    @NotNull(message = "Number od worksites can not be blank")
    @Positive(message = "Number od worksites must be a positive number")
    private Long numberOfWorksites;
    @NotNull(message = "X dimension can not be blank")
    @Positive(message = "X dimension must be a positive number")
    private Long dimensionX;
    @NotNull(message = "Y dimension can not be blank")
    @Positive(message = "Y dimension must be a positive number")
    private Long dimensionY;
}
