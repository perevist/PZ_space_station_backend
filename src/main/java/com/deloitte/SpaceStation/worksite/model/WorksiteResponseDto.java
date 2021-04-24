package com.deloitte.SpaceStation.worksite.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@Builder
public class WorksiteResponseDto {
    private Long worksiteId;
    private Long worksiteInRoomId;
    private Long roomId;
    private Long coordinateX;
    private Long coordinateY;
}
