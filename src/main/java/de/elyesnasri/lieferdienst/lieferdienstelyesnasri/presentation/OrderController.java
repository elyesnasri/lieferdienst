package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.presentation;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService.IOrderService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.DeliveryStatus;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Order;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order")
    public String getOrder (Model model) {
        model.addAttribute("order", new Order());
//        List<Parcel> parcels = new ArrayList<>();
//        parcels.add(new Parcel());
        return "order";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/order")
    public String addOrder(@ModelAttribute("order") Order order, RedirectAttributes redirectAttributes) {
        Date now = new Date();
        order.setOrderDate(now);
        // TODO: change gender to dropdown M | F
        // TODO: add assurance price for order
        // TODO: add parcelPRice with AssurancePrice to get totalprice
        order.setTotalPrice(10);
        DeliveryStatus delStatus = new DeliveryStatus();
        delStatus.setStatusName("Wird bearbeitet");
        delStatus.setDescription("Die Daten der Sendung wurden erfolgreich übermittelt.");
        order.setDeliveryStatus(delStatus);
        // TODO: generate parcel-Nr for delivery status
        order.setParcelNumber();
        // redirectAttributes.addFlashAttribute("order", order.getParcelType());
        orderService.sendParcel(order);

        // TODO: generate parcel-Nr for delivery status
        return "redirect:/confirm/"+ order.getParcelNumber();

        // TODO: redirect to confirm page or just show notification
        // TODO: in check status page: search order by parcel-Nr or LastName & PLZ
    }
}
