package com.rexwong.beans;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * ID修改器
 */
public class IdProptyEditor extends PropertyEditorSupport{

    public void setAsText(String text){
        if(StringUtils.hasText(text)){
            long id = Long.parseLong(text);
            setValue(id);
        }
        else{
            setValue(Long.MIN_VALUE);
        }
    }
}
