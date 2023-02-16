package com.app.markeet.connection.callbacks;

import com.app.markeet.model.Category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RespCategory implements Serializable {

    public String status = "";
    public List<Category> categories = new ArrayList<>();

}
