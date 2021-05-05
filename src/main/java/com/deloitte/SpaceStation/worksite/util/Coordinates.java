package com.deloitte.SpaceStation.worksite.util;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Data
public class Coordinates {
    @NotNull(message = "X dimension can not be blank")
    @Positive(message = "X dimension must be a positive number")
    private Long x;
    @NotNull(message = "Y dimension can not be blank")
    @Positive(message = "Y dimension must be a positive number")
    private Long y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
