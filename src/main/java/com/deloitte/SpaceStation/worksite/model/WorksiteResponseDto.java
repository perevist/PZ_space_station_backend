package com.deloitte.SpaceStation.worksite.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WorksiteResponseDto {
    private Long worksiteId;
    private Long worksiteInRoomId;
    private Long roomId;
}
