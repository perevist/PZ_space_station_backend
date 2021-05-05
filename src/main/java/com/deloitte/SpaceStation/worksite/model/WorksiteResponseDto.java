package com.deloitte.SpaceStation.worksite.model;

import com.deloitte.SpaceStation.worksite.util.Coordinates;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@Builder
public class WorksiteResponseDto {
    private Long worksiteId;
    private Long worksiteInRoomId;
    private Long roomId;
    private Coordinates coordinates;
}
