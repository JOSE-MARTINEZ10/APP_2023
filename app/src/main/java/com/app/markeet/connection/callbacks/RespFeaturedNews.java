package com.app.markeet.connection.callbacks;

import com.app.markeet.model.NewsInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RespFeaturedNews implements Serializable {

    public String status = "";
    public List<NewsInfo> news_infos = new ArrayList<>();

}
