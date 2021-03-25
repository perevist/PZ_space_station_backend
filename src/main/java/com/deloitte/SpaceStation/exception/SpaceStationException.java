package com.deloitte.SpaceStation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpaceStationException extends RuntimeException{
    private Error error;
}
