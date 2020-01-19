package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.presentation;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService.IOrderService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.parcelService.IParcelService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.DeliveryStatus;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Order;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@ControllerAdvice
public class OrderController {
    @Autowired
    private final IOrderService orderService;
    @Autowired
    private  final IParcelService parcelService;

    public OrderController(IOrderService orderService, IParcelService parcelService) {
        this.orderService = orderService;
        this.parcelService = parcelService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order")
    public String getOrder (Model model) {
        List<Parcel> parcels = (List<Parcel>) parcelService.getAllPArcels();
        model.addAttribute("parcels", parcels);
        model.addAttribute("order", new Order());
        return "order";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/order")
    public String addOrder(Model model, @ModelAttribute("order") @Valid Order order, Errors errors) {

        if (errors.hasErrors()) {
            return "order";
        }

        Date now = new Date();
        order.setOrderDate(now);
        // TODO: add assurance price for order
        // TODO: add parcelPRice with AssurancePrice to get totalprice
        order.setTotalPrice(10);
        DeliveryStatus delStatus = new DeliveryStatus();
        delStatus.setStatusName("Wird bearbeitet");
        delStatus.setDescription("Die Daten der Sendung wurden erfolgreich übermittelt.");
        order.setDeliveryStatus(delStatus);
        order.setParcelNumber();
        orderService.sendParcel(order);

        return "redirect:/confirm/"+ order.getOrderNumber();

    }
}
