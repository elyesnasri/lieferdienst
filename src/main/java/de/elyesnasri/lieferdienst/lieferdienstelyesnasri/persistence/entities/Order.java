package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Valid
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "recipientId")
    private Customer recipient;

    @Valid
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "senderId")
    private Customer sender;

    @OneToOne // TODO change to manytoone
    private Parcel parcelTypes;

    private String orderNumber;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    private int totalPrice;

    @ManyToOne(cascade = {CascadeType.ALL})
    private DeliveryStatus deliveryStatus;

    @NotEmpty (message = "Bitten geben Sie Ihre Iban ein.")
    private String senderIban;

    @NotNull(message = "Bitten geben Sie das Password ein.")
    private long senderAccountId;

    @NotEmpty (message = "Bitten geben Sie das Password ein.")
    private String senderAccountPassword;


    public Customer getRecipient() {
        return recipient;
    }

    public void setRecipient(Customer recipient) {
        this.recipient = recipient;
    }
    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Parcel getParcelTypes() {
        return parcelTypes;
    }

    public void setParcelTypes(Parcel parcelTypes) {
        this.parcelTypes = parcelTypes;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setParcelNumber() {
        String code = UUID.randomUUID().toString();
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        this.orderNumber = code.concat(date);
    }

    public String getSenderIban() {
        return senderIban;
    }

    public void setSenderIban(String senderIban) {
        this.senderIban = senderIban;
    }

    public String getSenderAccountPassword() {
        return senderAccountPassword;
    }

    public void setSenderAccountPassword(String senderAccountPassword) {
        this.senderAccountPassword = senderAccountPassword;
    }

    public long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }
}
