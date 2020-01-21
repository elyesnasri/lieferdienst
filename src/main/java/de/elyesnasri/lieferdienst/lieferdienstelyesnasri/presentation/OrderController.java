package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.presentation;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.deliveryStatusService.IDeliveryStatusService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService.IOrderService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService.SendOrder;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.parcelService.IParcelService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.*;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@ControllerAdvice
public class OrderController {
    @Autowired
    private final IOrderService orderService;
    @Autowired
    private  final IParcelService parcelService;
    @Autowired
    private final IDeliveryStatusService deliveryStatusService;

    public OrderController(IOrderService orderService, IParcelService parcelService, IDeliveryStatusService deliveryStatusService) {
        this.orderService = orderService;
        this.parcelService = parcelService;
        this.deliveryStatusService = deliveryStatusService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order")
    public String getOrder (Model model) {
        List<Parcel> parcels = (List<Parcel>) parcelService.getAllParcels();
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
        order.setTotalPrice(order.getParcelTypes().getPrice());

        Optional<DeliveryStatus> delStatus = this.deliveryStatusService.getDeliveryStatus(Status.Step1.getText());
        if (delStatus.isPresent()) {
            order.setDeliveryStatus(delStatus.get());
        }

        order.setParcelNumber();

        boolean payment = orderService.sendParcel(order);
        if (!payment) {
            return "order";
        }

        return "redirect:/confirm/"+ order.getOrderNumber();
    }

    @RequestMapping(value = "apis/order/makeOrder", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Boolean makeOrder (@RequestBody SendOrder sendOrder) {
        Order order = new Order();
        Customer sender = new Customer();
        Customer recipient = new Customer();

        sender.setPersonalData(sendOrder.getSenderData());
        recipient.setPersonalData(sendOrder.getRecipientData());
        order.setSender(sender);
        order.setRecipient(recipient);

        Optional<DeliveryStatus> delStatus = this.deliveryStatusService.getDeliveryStatus(Status.Step1.getText());
        if (delStatus.isPresent()) {
            order.setDeliveryStatus(delStatus.get());
        }

        order.setParcelNumber();
        Date now = new Date();
        order.setOrderDate(now);

        int sendOrderParcelWeight = sendOrder.getParcelWeight();
        Parcel parcel = checkParcelType(sendOrderParcelWeight);
        order.setParcelTypes(parcel);
        order.setTotalPrice(parcel.getPrice());

        order.setSenderAccountId(sendOrder.getSenderAccountId());
        order.setSenderAccountPassword(sendOrder.getSenderAccountPassword());
        order.setSenderIban(sendOrder.getSenderIban());

        boolean payment = orderService.sendParcel(order);
        if (!payment) {
            return false;
        }

        return true;
    }

    Parcel checkParcelType (int weight){
        if (weight <= 2) {
            Optional<Parcel> parcel = this.parcelService.getParcel(2);
            if (parcel.isPresent()) {
                return parcel.get();
            }
        } else if (weight <= 5) {
            Optional<Parcel> parcel = this.parcelService.getParcel(5);
            if (parcel.isPresent()) {
                return parcel.get();
            }
        }

        Parcel parcelByType = null;
        Optional<Parcel> parcel = this.parcelService.getParcel(10);
        if (parcel.isPresent()) {
            parcelByType = parcel.get();
        }
        return parcelByType;
    }
}
