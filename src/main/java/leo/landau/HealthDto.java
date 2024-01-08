package leo.landau;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class HealthDto {
    @JsonProperty
    private String status;

    public HealthDto(String status) {
        this.status = status;
    }

}
