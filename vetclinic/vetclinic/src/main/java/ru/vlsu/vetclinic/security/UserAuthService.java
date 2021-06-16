package ru.vlsu.vetclinic.security;

import javassist.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vlsu.vetclinic.persistence.UserRepository;

import java.util.Collections;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserAuthService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return repository.findByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getRoles()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
