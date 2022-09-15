package com.shopping.shopping.data.member;

import java.util.Date;

import lombok.Data;

@Data
public class MemberInfoVO {
    private Integer mi_seq;
    private Integer mi_mg_seq;
    private String mi_id;
    private String mi_pwd;
    private String mi_name;
    private String mi_nickname;
    private Date mi_birth;
    private Integer mi_gen;
    private Integer mi_point;
    private String mi_image;
    private Date mi_reg_dt;
    private Integer mi_status;
}
