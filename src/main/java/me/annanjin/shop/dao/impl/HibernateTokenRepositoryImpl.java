package me.annanjin.shop.dao.impl;

import me.annanjin.shop.entity.PersistentLogin;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository(value = "HibernateRepository")
@Transactional
public class HibernateTokenRepositoryImpl implements PersistentTokenRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(persistentRememberMeToken.getUsername());
        persistentLogin.setSeries(persistentRememberMeToken.getSeries());
        persistentLogin.setLast_used(persistentRememberMeToken.getDate());
        persistentLogin.setToken(persistentRememberMeToken.getTokenValue());
        entityManager.persist(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLogin persistentLogin = entityManager.find(PersistentLogin.class, seriesId);
        if (persistentLogin == null) return null;
        System.out.println(persistentLogin.getUsername());
        return new PersistentRememberMeToken(persistentLogin.getUsername(),
                persistentLogin.getSeries(), persistentLogin.getToken(), persistentLogin.getLast_used());
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date last_used) {
        PersistentLogin persistentLogin = entityManager.find(PersistentLogin.class, seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(last_used);
        entityManager.merge(persistentLogin);
    }

    @Override
    public void removeUserTokens(String username) {
        PersistentLogin persistentLogin = entityManager
                .createQuery("SELECT p FROM PersistentLogin p WHERE p.username = :username", PersistentLogin.class)
                .setParameter("username", username).getSingleResult();
        if (persistentLogin != null) {
            entityManager.remove(persistentLogin);
        }
    }
}
