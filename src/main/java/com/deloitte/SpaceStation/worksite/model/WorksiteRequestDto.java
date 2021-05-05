package com.deloitte.SpaceStation.worksite.model;

import com.deloitte.SpaceStation.worksite.util.Coordinates;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Builder
public class WorksiteRequestDto {
    @NotNull(message = "worksiteInRoomId can not be blank")
    private Long worksiteInRoomId;
    private Coordinates coordinates;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorksiteRequestDto that = (WorksiteRequestDto) o;
        return Objects.equals(worksiteInRoomId, that.worksiteInRoomId) &&
                Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worksiteInRoomId, coordinates);
    }
}
