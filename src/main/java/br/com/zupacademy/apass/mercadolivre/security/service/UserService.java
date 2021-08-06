package br.com.zupacademy.apass.mercadolivre.security.service;

import br.com.zupacademy.apass.mercadolivre.model.Usuario;
import br.com.zupacademy.apass.mercadolivre.security.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

   @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<Usuario> users = this.entityManager.createQuery("FROM Usuario WHERE login = :pLogin", Usuario.class)
                .setParameter("pLogin", userName)
                .getResultList();

        Assert.state(users.size() <= 1, "Foi encontrado mais de um usuário com o mesmo login!");

        if(users.isEmpty()) {
            throw new UsernameNotFoundException("Não foi encontrado nenhum usuário com o e-mail " + userName);
        }

        return new User(users.get(0));
    }
}
