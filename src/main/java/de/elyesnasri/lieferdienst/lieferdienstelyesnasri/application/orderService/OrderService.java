package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.orderService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Order;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories.IOrderRepository;
import de.marcoedenhofer.edenbank.application.transactionservice.TransactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private final IOrderRepository orderRepository;

    @Autowired
    private final RestTemplate restClient;

    public OrderService(IOrderRepository orderRepository, RestTemplate restClient) {
        this.orderRepository = orderRepository;
        this.restClient = restClient;
    }

    @Override
    public boolean sendParcel(Order order) {
        // send transaction to marco.edenbank
        TransactionData transactionData = new TransactionData();

        transactionData.setAmount(order.getTotalPrice());

        transactionData.setSenderIban(order.getSenderIban());
        transactionData.setSenderCustomerAccountId(order.getSenderAccountId());
        transactionData.setSenderPassword(order.getSenderAccountPassword());

        transactionData.setReceiverIban("DE750300110000000004");
        transactionData.setUsageDetails("Lieferdienst sagt danke :)");

        ResponseEntity<TransactionData> payment = restClient.postForEntity("http://im-codd:8847/apis/transaction/execute", transactionData, TransactionData.class);
        if (!payment.getStatusCode().is2xxSuccessful()){
            return false;
        }

        this.orderRepository.save(order);
        return true;
    }

    @Override
    public Optional<Order> getParcel(long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        return order;
    }

    @Override
    public Optional<Order> getParcelByNumber(String parcelNumber) {
        Optional<Order> order = this.orderRepository.findOrderByorderNumber(parcelNumber);
        return order;
    }
}
