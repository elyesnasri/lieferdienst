package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.presentation;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService.IOrderService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Order;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.SearchOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class TrackingController {
    @Autowired
    private final IOrderService orderService;

    public TrackingController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tracking")
    public String getOrder (Model model) {
        model.addAttribute("searchOrder", new SearchOrder());
        return "tracking";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tracking")
    public String searchOrder(@ModelAttribute("searchOrder") @Valid SearchOrder searchOrder, Model model) {
        String lastName = searchOrder.getLastName();
        int postalCode = searchOrder.getPlz();

        Optional<Order> order = this.orderService.getParcelByNumber(searchOrder.getOrderNumber());
        if (order.isPresent()) {
            String getLastName = order.get().getRecipient().getPersonalData().getLastName();
            int getPostalCode = order.get().getRecipient().getPersonalData().getAddress().getPostalCode();

            if (!getLastName.equals(lastName) || (getPostalCode != postalCode)) {
                model.addAttribute("error", "Die eingegebenen Daten stimmen nicht mit den gespeicherten überein.");
                return "tracking";
            }
        } else {
            model.addAttribute("error", "Es liegen keine Infromationen für die Sendung vor.");
            return "tracking";
        }

        return "redirect:/trackingstatus/" + order.get().getOrderNumber();
    }
}
