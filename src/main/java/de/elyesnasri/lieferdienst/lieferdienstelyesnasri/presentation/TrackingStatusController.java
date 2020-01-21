package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.presentation;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.deliveryStatusService.IDeliveryStatusService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService.IOrderService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.DeliveryStatus;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Order;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    @Autowired
    private  final IDeliveryStatusService deliveryStatusService;
    private int Step = 2;
    private Order updateOrder = null;

    public TrackingStatusController(IOrderService orderService, IDeliveryStatusService deliveryStatusService) {
        this.orderService = orderService;
        this.deliveryStatusService = deliveryStatusService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/trackingstatus/{number}")
    public String getOrderStatus (Model model, @PathVariable("number") String number) {
        Optional<Order> order = this.orderService.getParcelByNumber(number);
        order.ifPresent(o -> model.addAttribute("order", o));
        if (order.isPresent()) {
//            model.addAttribute("order", order);
            updateOrder = order.get();
            // change status every 10 sec
            autoChangeStatus();
        }

        return "trackingstatus";
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void autoChangeStatus() {
        if (updateOrder != null) {
            if (Step == 1) {
                Step = 2;
                Optional<DeliveryStatus> delStatus = this.deliveryStatusService.getDeliveryStatus(Status.Step2.getText());
                if (delStatus.isPresent()) {
                    updateOrder.setDeliveryStatus(delStatus.get());
                }
            } else if (Step == 2) {
                Step = 3;
                Optional<DeliveryStatus> delStatus = this.deliveryStatusService.getDeliveryStatus(Status.Step3.getText());
                if (delStatus.isPresent()) {
                    updateOrder.setDeliveryStatus(delStatus.get());
                }
            }

            // persist order to DB
            this.orderService.updateOrderStatus(updateOrder);
        }
    }
}
