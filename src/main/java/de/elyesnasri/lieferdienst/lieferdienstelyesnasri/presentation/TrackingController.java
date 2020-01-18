package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.presentation;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService.IOrderService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Order;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.SearchOrder;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
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
    public String searchOrder(@ModelAttribute("searchOrder") SearchOrder searchOrder, Model model, RedirectAttributes redirectAttributes) {
        String lastName = searchOrder.getLastName();
        int postalCode = searchOrder.getPlz();

        Optional<Order> order = this.orderService.getParcelByNumber(searchOrder.getParcelNumber());
        if (order.isPresent()) {
            String getLastName = order.get().getRecipient().getPersonalData().getLastName();
            int getPostalCode = order.get().getRecipient().getPersonalData().getAddress().getPostalCode();

            if (!getLastName.equals(lastName) || (getPostalCode != postalCode)) {
                model.addAttribute("error", "Es liegen keine Infromationen für die Sendung vor.");
                return "tracking";
            }
        } else {
            model.addAttribute("error", "Es liegen keine Infromationen für die Sendung vor.");
            return "tracking";
        }

        // TODO show error not found
        return "redirect:/trackingstatus/" + order.get().getParcelNumber();
    }
}
