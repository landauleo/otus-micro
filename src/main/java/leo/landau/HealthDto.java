package leo.landau;

import com.fasterxml.jackson.annotation.JsonProperty;


public class HealthDto {
    @JsonProperty
    private String status;

    public HealthDto(String status) {
        this.status = status;
    }

}
