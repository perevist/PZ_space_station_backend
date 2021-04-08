package com.deloitte.SpaceStation.worksite.service;

import com.deloitte.SpaceStation.room.model.Room;
import com.deloitte.SpaceStation.worksite.model.Worksite;
import com.deloitte.SpaceStation.worksite.model.WorksiteResponseDto;
import com.deloitte.SpaceStation.worksite.repository.WorksiteRepository;
import com.deloitte.SpaceStation.worksite.util.WorksiteResponseDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorksiteServiceImpl implements WorksiteService {

    private final WorksiteRepository worksiteRepository;

    @Override
    public List<WorksiteResponseDto> getAllWorksites() {
        return worksiteRepository.findAll().stream()
                .map(WorksiteResponseDtoMapper::mapToWorksiteResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorksiteResponseDto> getWorksitesByRoom(Long roomId) {
        return worksiteRepository.getAllByRoom(roomId).stream()
                .map(WorksiteResponseDtoMapper::mapToWorksiteResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorksiteResponseDto> getWorksitesByAvailabilityDate(LocalDate startDate, LocalDate endDate) {
        return worksiteRepository.getAllByAvailabilityDate(startDate, endDate).stream()
                .map(WorksiteResponseDtoMapper::mapToWorksiteResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorksiteResponseDto> getWorksitesByRoomAndAvailabilityDate(
            Long roomId, LocalDate startDate, LocalDate endDate) {
        return worksiteRepository.getAllByRoomAndAvailabilityDate(roomId, startDate, endDate).stream()
                .map(WorksiteResponseDtoMapper::mapToWorksiteResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Worksite addWorksite(Long worksiteInRoomId, Room room) {
        Worksite worksite = new Worksite();
        worksite.setWorksiteInRoomId(worksiteInRoomId);
        worksite.setRoom(room);
        worksite = worksiteRepository.save(worksite);
        return worksite;
    }
}