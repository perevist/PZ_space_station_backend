package com.deloitte.SpaceStation.worksite.util;

import com.deloitte.SpaceStation.worksite.model.Worksite;
import com.deloitte.SpaceStation.worksite.model.WorksiteResponseDto;
import org.springframework.stereotype.Component;

@Component
public class WorksiteResponseDtoMapper {

    private WorksiteResponseDtoMapper() {
    }

    public static WorksiteResponseDto mapToWorksiteResponseDto(Worksite worksite) {

        return WorksiteResponseDto.builder()
                .worksiteId(worksite.getId())
                .worksiteInRoomId(worksite.getWorksiteInRoomId())
                .roomId(worksite.getRoom().getId())
                .coordinates(new Coordinates(worksite.getCoordinateX(), worksite.getCoordinateY()))
                .build();
    }
}
