package com.admin.app.dto;

import lombok.Data;


@Data
public class AdminCode extends PagingInfo{

    private Long id;
    private String depth1;
    private String depth2;
    private String admcode;
    private String fareType1;
    private String fareType2;
    private String fareTypeW;
    private String fareTypeEtc;
    private String editType;
    private String fareTypeNm;

}
