package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.dao.RoleDAO;
import me.annanjin.shop.entity.RoleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Repository
@Transactional
public class RoleDaoImpl extends DAOAbstract<Integer, RoleEntity> implements RoleDAO {
    @Override
    public RoleEntity getByName(String name) {
        try {
            return (RoleEntity) this.entityManager.createQuery("SELECT r FROM RoleEntity r WHERE r.roleName = :name")
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}