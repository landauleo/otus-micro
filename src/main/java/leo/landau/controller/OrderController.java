package leo.landau.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import leo.landau.model.Order;
import leo.landau.service.OrderService;

@Tag(name = "user")
@Controller("/order")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class OrderController {

    @Inject
    private OrderService orderService;

    @Post
    public HttpResponse<Order> createOrder(@RequestBody Order order) {
        return HttpResponse.ok(orderService.createOrder(order));
    }

    @Get("/{id}")
    public HttpResponse<Order> getOrder(@PathVariable Long id) {
        return HttpResponse.ok(orderService.getOrder(id));
    }

}
