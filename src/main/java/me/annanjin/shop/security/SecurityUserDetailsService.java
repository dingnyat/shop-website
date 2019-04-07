package me.annanjin.shop.security;

import me.annanjin.shop.dao.AccountDAO;
import me.annanjin.shop.entity.AccountEntity;
import me.annanjin.shop.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service(value = "CustomUserDetailsService")
@Transactional(readOnly = true)
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountDAO.getByUsername(username);
        if (accountEntity != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            Set<RoleEntity> roles = accountEntity.getRoles();
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
            return new User(accountEntity.getUsername(), accountEntity.getPassword(), authorities);
        }
        return null;
    }
}
