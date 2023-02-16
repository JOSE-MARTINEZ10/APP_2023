package com.app.markeet.connection.callbacks;

import com.app.markeet.model.Order;

import java.io.Serializable;

public class RespOrderHistory implements Serializable {

    public String status = "";
    public Order product_order = new Order();

}
