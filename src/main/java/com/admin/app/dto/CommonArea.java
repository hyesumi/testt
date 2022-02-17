package com.admin.app.dto;

import lombok.Data;


@Data
public class CommonArea {

    private Long id;
    private String areaName;
    private String areaCode;
    private String extraData;
    private String effectiveTs;
    private String createTs;
}
