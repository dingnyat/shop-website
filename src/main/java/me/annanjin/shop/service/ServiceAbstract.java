package me.annanjin.shop.service;

import me.annanjin.shop.dao.DAOInterface;
import me.annanjin.shop.model.CommonModel;
import me.annanjin.shop.utils.BeanTools;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public abstract class ServiceAbstract<PrimaryKeyType extends Serializable, M extends CommonModel, E, D extends DAOInterface> {

    protected D repository;

    private Class<E> entityClazz;

    private Class<M> modelClazz;

    protected BeanTools beanTools;

    public ServiceAbstract(D repository, BeanTools beanTools) {
        this.repository = repository;
        this.beanTools = beanTools;
        this.entityClazz = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        this.modelClazz = (Class<M>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public PrimaryKeyType add(M model) {
        try {
            return (PrimaryKeyType) repository.add(beanTools.convert(model, entityClazz.getConstructor().newInstance()));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(M model) {
        E entity = (E) repository.getById(model.getId());
        beanTools.convert(model, entity);
        repository.update(entity);
    }

    public void remove(PrimaryKeyType id) {
        E entity = (E) repository.getById(id);
        repository.remove(entity);
    }

    public M getById(PrimaryKeyType id) {
        E entity = (E) repository.getById(id);
        try {
            return beanTools.convert(entity, modelClazz.getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<M> getAll() {
        List<E> entityList = repository.getAll();
        return entityList.stream()
                .map(entity -> {
                    try {
                        return beanTools.convert(entity, modelClazz.getConstructor().newInstance());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}
