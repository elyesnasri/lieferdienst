package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.deliveryStatusService.IDeliveryStatusService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.DeliveryStatus;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Order;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.enums.Status;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories.IOrderRepository;
import de.marcoedenhofer.edenbank.application.transactionservice.TransactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private final IOrderRepository orderRepository;
    @Autowired
    private final IDeliveryStatusService deliveryStatusService;

    private static final String step1 = Status.Step1.getText();
    private static final String step2 = Status.Step2.getText();
    private static final String step3 = Status.Step3.getText();

    @Autowired
    private final RestTemplate restClient;

    public OrderService(IOrderRepository orderRepository, IDeliveryStatusService deliveryStatusService, RestTemplate restClient) {
        this.orderRepository = orderRepository;
        this.deliveryStatusService = deliveryStatusService;
        this.restClient = restClient;
    }

    @Override
    public boolean sendOrder(Order order) {
        // send transaction to marco.edenbank
        TransactionData transactionData = new TransactionData();

        transactionData.setAmount(order.getTotalPrice());

        transactionData.setSenderIban(order.getSenderIban());
        transactionData.setSenderCustomerAccountId(order.getSenderAccountId());
        transactionData.setSenderPassword(order.getSenderAccountPassword());

        transactionData.setReceiverIban("DE750300110000000004");
        transactionData.setUsageDetails("Lieferdienst sagt danke :)");

        ResponseEntity<TransactionData> payment = restClient.postForEntity("http://im-codd:8847/apis/transaction/execute", transactionData, TransactionData.class);
        if (payment.getStatusCode().is2xxSuccessful()){
            this.orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Order> getOrder(long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        return order;
    }

    @Override
    public Optional<Order> getOrderByNumber(String orderNumber) {
        Optional<Order> order = this.orderRepository.findOrderByorderNumber(orderNumber);
        return order;
    }

    @Override
    public void updateOrderStatus(Order order) {
        this.orderRepository.save(order);
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void autoChangeStatus() {
        Iterable<Order> orders = this.orderRepository.findAll();
        for (Order order: orders) {
            String orderStatus = order.getDeliveryStatus().getName();
            if (orderStatus.equals(step1)) {
                Optional<DeliveryStatus> delStatus = this.deliveryStatusService.getDeliveryStatus(Status.Step2.getText());
                if (delStatus.isPresent()) {
                    order.setDeliveryStatus(delStatus.get());
                    this.updateOrderStatus(order);
                }
            } else if (orderStatus.equals(step2)) {
                Optional<DeliveryStatus> delStatus = this.deliveryStatusService.getDeliveryStatus(Status.Step3.getText());
                if (delStatus.isPresent()) {
                    order.setDeliveryStatus(delStatus.get());
                    this.updateOrderStatus(order);
                }
            }
        }

    }
}
