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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String searchOrder(@ModelAttribute("searchOrder") SearchOrder searchOrder, RedirectAttributes redirectAttributes) {
        Optional<Order> order;
        String lastName = searchOrder.getLastName();
        int plz = searchOrder.getPlz();
        String code;



        if ((searchOrder.getParcelNumber() != null) || (!searchOrder.getParcelNumber().isEmpty())) {
            order = this.orderService.getParcelByNumber(searchOrder.getParcelNumber());
        } else {
            order = this.orderService.getParcelByNamePLZ(lastName, plz);
        }
        // TODO search for the order per number or name+plz
        return "redirect:/trackingstatus/" +searchOrder.getParcelNumber();
    }
}
