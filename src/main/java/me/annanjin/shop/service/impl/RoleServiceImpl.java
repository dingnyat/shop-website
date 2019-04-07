package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.RoleDAO;
import me.annanjin.shop.entity.RoleEntity;
import me.annanjin.shop.model.Role;
import me.annanjin.shop.service.RoleService;
import me.annanjin.shop.service.ServiceAbstract;
import me.annanjin.shop.util.BeanTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends ServiceAbstract<Integer, Role, RoleEntity, RoleDAO> implements RoleService {
    public RoleServiceImpl(@Autowired RoleDAO repository, @Autowired BeanTools beanTools) {
        super(repository, beanTools);
    }
}
