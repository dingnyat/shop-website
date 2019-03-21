package me.annanjin.shop.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BeanTools {
    public <M, E> E convert(M sourceObject, E targetObject) {
        BeanUtils.copyProperties(sourceObject, targetObject);
        return targetObject;
    }
}
