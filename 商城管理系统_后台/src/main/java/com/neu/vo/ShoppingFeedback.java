package com.neu.vo;

import lombok.Data;

@Data
public class ShoppingFeedback {
    private String id;
    private String user_id;
    private String username;
    private String mobile;
    private String feed_type;
    private String content;
    private String status;
    private String has_picture;
    private String pic_urls;
    private String add_time;
    private String update_time;
    private String deleted;

}
