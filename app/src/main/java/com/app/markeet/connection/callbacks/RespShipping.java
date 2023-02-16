package com.app.markeet.connection.callbacks;

import com.app.markeet.model.Shipping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RespShipping implements Serializable {

    public String status = "";
    public List<Shipping> shipping = new ArrayList<>();

}
