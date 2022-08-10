package com.ecommerce.imobiliaria.Services;

import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Registro.Token.ConfirmationToken;
import com.ecommerce.imobiliaria.Registro.Token.ConfirmationTokenRepository;
import com.ecommerce.imobiliaria.Registro.Token.ConfirmationTokenService;
import com.ecommerce.imobiliaria.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG = "Usuário: %s não encontrado";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private ConfirmationTokenRepository confirmationTokenRepository;
    private final RoleService roleService;

    public List<User> listar() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
                 if(user== null) {
                     throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username));
                 } else {
                    log.info("Usuário: {} encontrado", username);
                 }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                 user.get().getRoles().forEach(role -> {
                     authorities.add(new SimpleGrantedAuthority(role.getName()));
                 });
                 return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                null,
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                null,
                user

        );
        if (userExists) {
            User userExistente = userRepository.findByUsername(user.getUsername()).get();
            boolean enabled = userExistente.isEnabled();
            if (!enabled) {
                String token1 = confirmationTokenRepository.findByUserId(userExistente.getIdUser()).get().getToken();
                confirmationTokenService.setExpirouEm(token1);
                return token1;
            }else {
                throw new IllegalStateException("Usuário já existe");

            }
        }  else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setDataCriacao(LocalDateTime.now());
            user.setDataAtualizacao(LocalDateTime.now());
            userRepository.save(user);
                confirmationTokenService.saveConfirmationToken(confirmationToken);
            return token;
        }


    }

    public int enableUser(String email){
        return userRepository.enableUser(email);
    }

    //delete user
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
