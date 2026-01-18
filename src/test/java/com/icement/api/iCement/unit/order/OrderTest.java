package com.icement.api.iCement.unit.order;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.icement.api.iCement.common.entities.Address;
import com.icement.api.iCement.order.Order;
import com.icement.api.iCement.order.OrderItem;
import com.icement.api.iCement.order.enums.OrderStatus;

class OrderTest {

    private Address validAddress;
    private List<OrderItem> validItems;

    @BeforeEach
    void setUp() {
        validAddress = Address.builder()
                .street("123 Main St")
                .city("Accra")
                .country("Ghana")
                .build();

        OrderItem item = OrderItem.builder()
                .productName("Cement Bag")
                .productNumber("CEM-001")
                .price(new BigDecimal("50.00"))
                .quantity(10)
                .totalPrice(new BigDecimal("500.00"))
                .build();

        validItems = new ArrayList<>();
        validItems.add(item);
    }

    @Test
    void create_withValidInputs_returnsOrderWithPendingStatus() {
        Order order = Order.create(1, validItems, validAddress);

        assertEquals(OrderStatus.PENDING, order.getStatus());
        assertEquals(1, order.getRetailerId());
        assertEquals(1, order.getItems().size());
        assertNotNull(order.getShippingAddress());
    }

    @Test
    void create_withNullItems_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            Order.create(1, null, validAddress));
    }

    @Test
    void create_withEmptyItems_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            Order.create(1, new ArrayList<>(), validAddress));
    }

    @Test
    void create_withNullAddress_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            Order.create(1, validItems, null));
    }

    @Test
    void create_withNullRetailerId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            Order.create(null, validItems, validAddress));
    }

    @Test
    void create_calculatesTotalsCorrectly() {
        Order order = Order.create(1, validItems, validAddress);

        assertEquals(new BigDecimal("500.00"), order.getTotalNetPrice());
        assertEquals(new BigDecimal("95.0000"), order.getTaxAmount());
    }

    @Test
    void confirm_fromPending_changesStatusToConfirmed() {
        Order order = Order.create(1, validItems, validAddress);

        order.confirm();

        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    void confirm_fromDelivered_throwsException() {
        Order order = createOrderInState(OrderStatus.DELIVERED);

        assertThrows(IllegalStateException.class, order::confirm);
    }

    @Test
    void startProduction_fromConfirmed_changesStatusToInProduction() {
        Order order = Order.create(1, validItems, validAddress);
        order.confirm();

        order.startProduction();

        assertEquals(OrderStatus.IN_PRODUCTION, order.getStatus());
    }

    @Test
    void assignToDriver_fromInProduction_changesStatusAndSetsDriverId() {
        Order order = Order.create(1, validItems, validAddress);
        order.startProduction();

        order.assignToDriver(42);

        assertEquals(OrderStatus.ASSIGNED_TO_DRIVER, order.getStatus());
        assertEquals(42, order.getDriverId());
    }

    @Test
    void dispatch_fromAssignedToDriver_changesStatusToOutForDelivery() {
        Order order = Order.create(1, validItems, validAddress);
        order.startProduction();
        order.assignToDriver(42);

        order.dispatch();

        assertEquals(OrderStatus.OUT_FOR_DELIVERY, order.getStatus());
    }

    @Test
    void deliver_fromOutForDelivery_changesStatusToDelivered() {
        Order order = Order.create(1, validItems, validAddress);
        order.startProduction();
        order.assignToDriver(42);
        order.dispatch();

        order.deliver();

        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    void deliver_fromPending_throwsException() {
        Order order = Order.create(1, validItems, validAddress);

        assertThrows(IllegalStateException.class, order::deliver);
    }

    @Test
    void cancel_fromPending_changesStatusToCancelled() {
        Order order = Order.create(1, validItems, validAddress);

        order.cancel();

        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void cancel_fromDelivered_throwsException() {
        Order order = createOrderInState(OrderStatus.DELIVERED);

        assertThrows(IllegalStateException.class, order::cancel);
    }

    @Test
    void hold_fromPending_changesStatusToOnHold() {
        Order order = Order.create(1, validItems, validAddress);

        order.hold();

        assertEquals(OrderStatus.ON_HOLD, order.getStatus());
    }

    @Test
    void hold_fromDelivered_throwsException() {
        Order order = createOrderInState(OrderStatus.DELIVERED);

        assertThrows(IllegalStateException.class, order::hold);
    }

    @Test
    void addItem_recalculatesTotals() {
        Order order = Order.create(1, validItems, validAddress);
        BigDecimal initialTotal = order.getTotalNetPrice();

        OrderItem newItem = OrderItem.builder()
                .productName("Another Cement")
                .productNumber("CEM-002")
                .price(new BigDecimal("60.00"))
                .quantity(5)
                .totalPrice(new BigDecimal("300.00"))
                .build();

        order.addItem(newItem);

        assertEquals(initialTotal.add(new BigDecimal("300.00")), order.getTotalNetPrice());
    }

    @Test
    void addItem_withNull_throwsException() {
        Order order = Order.create(1, validItems, validAddress);

        assertThrows(IllegalArgumentException.class, () -> order.addItem(null));
    }

    private Order createOrderInState(OrderStatus targetStatus) {
        Order order = Order.create(1, validItems, validAddress);
        order.startProduction();
        order.assignToDriver(1);
        order.dispatch();
        order.deliver();
        return order;
    }
}
