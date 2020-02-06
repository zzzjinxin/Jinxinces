package com.aaa.demo.dto;

import lombok.Data;

/**
 * @ClassName 金鑫
 * @Description: TODO
 * @Author yinwe
 * @Date 2020/2/4
 * @Version V1.0
 **/
@Data
public class AccessTokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String  state;
}
