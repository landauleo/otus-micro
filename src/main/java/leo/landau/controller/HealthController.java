package leo.landau.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import leo.landau.model.HealthDto;

@Controller("/app/health")
public class HealthController {

    @Get
    public HttpResponse<HealthDto> index() {
        return HttpResponse.ok().body(new HealthDto("OK"));
    }

}