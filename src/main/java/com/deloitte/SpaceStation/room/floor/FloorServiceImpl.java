package com.deloitte.SpaceStation.room.floor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService {
    private final FloorRepository floorRepository;

    @Override
    public Long getFloorQuantity() {
        return floorRepository.getFloorQuantity();
    }

}
