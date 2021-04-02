package com.deloitte.SpaceStation.worksite.controller;

import com.deloitte.SpaceStation.util.RequestDateValidator;
import com.deloitte.SpaceStation.worksite.model.WorksiteResponseDto;
import com.deloitte.SpaceStation.worksite.service.WorksiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/worksites")
public class WorksiteController {

    private final RequestDateValidator requestDateValidator;
    private final WorksiteService worksiteService;


    @GetMapping("/list")
    public List<WorksiteResponseDto> getWorksites(@RequestParam(required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                  @RequestParam(required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                  @RequestParam(required = false) Long roomId) {

        if (startDate != null || endDate != null) {
            requestDateValidator.validatePassedDates(startDate, endDate);
            if (roomId != null) {
                return worksiteService.getWorksitesByRoomAndAvailabilityDate(roomId, startDate, endDate);
            }
            return worksiteService.getWorksitesByAvailabilityDate(startDate, endDate);
        } else if (roomId != null) {
            return worksiteService.getWorksitesByRoom(roomId);
        } else {
            return worksiteService.getAllWorksites();
        }
    }
}





