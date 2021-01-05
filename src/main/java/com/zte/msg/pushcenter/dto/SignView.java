package com.zte.msg.pushcenter.dto;

import lombok.Data;

@Data
public class SignView {
    private String aa;

    @Override
    public String toString() {
        return "SignView{" +
                "aa='" + aa + '\'' +
                '}';
    }
}
