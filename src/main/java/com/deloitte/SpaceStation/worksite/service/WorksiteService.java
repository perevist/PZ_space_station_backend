package com.deloitte.SpaceStation.worksite.service;

import com.deloitte.SpaceStation.room.model.Room;
import com.deloitte.SpaceStation.worksite.model.Worksite;
import com.deloitte.SpaceStation.worksite.model.WorksiteResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface WorksiteService {
    List<WorksiteResponseDto> getAllWorksites();

    List<WorksiteResponseDto> getWorksitesByRoom(Long roomId);

    List<WorksiteResponseDto> getWorksitesByAvailabilityDate(LocalDate startDate, LocalDate endDate);

    List<WorksiteResponseDto> getWorksitesByRoomAndAvailabilityDate(Long roomId, LocalDate startDate, LocalDate endDate);

    Worksite addWorksite(Long worksiteInRoomId, Room room);
}
