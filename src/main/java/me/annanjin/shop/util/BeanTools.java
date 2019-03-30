package me.annanjin.shop.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BeanTools {

    private ModelMapper modelMapper;

    public BeanTools() {
        modelMapper = new ModelMapper();
    }

    public <M, E> E convert(M source, E target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public <M, E> E map(M source, E target) {
        modelMapper.map(source, target);
        return target;
    }
}
