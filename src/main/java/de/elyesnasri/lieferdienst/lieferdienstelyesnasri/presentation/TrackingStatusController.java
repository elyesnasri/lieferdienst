package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.presentation;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService.IOrderService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class TrackingStatusController {

    @Autowired
    private final IOrderService orderService;

    public TrackingStatusController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/trackingstatus/{number}")
    public String getOrderStatus (Model model, @PathVariable("number") String number) {
        Optional<Order> order = this.orderService.getParcelByNumber(number);
        order.ifPresent(o -> model.addAttribute("order", o));
//        model.addAttribute("order", order);
        return "trackingstatus";
    }
}
