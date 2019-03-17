package me.annanjin.shop.dao;

import me.annanjin.shop.entity.RoleEntity;

public interface RoleDAO extends DAOInterface<Integer, RoleEntity> {
    RoleEntity getByName(String name);
}
