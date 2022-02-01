package com.administration.config.oauth.service;

import com.administration.config.oauth.entities.UserDetailsImpl;
import com.administration.model.User;
import com.administration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndActifIsNot(s, -1).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + s));
        UserDetailsImpl userDetails = new UserDetailsImpl();

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        String sql = "SELECT DISTINCT h.code FROM habilitations h " +
                "INNER JOIN habilitation_par_profil hp ON h.id = hp.habilitation_id " +
                "INNER JOIN profils r ON r.profil_id = hp.role_id " +
                "INNER JOIN users u ON u.profil_id = r.profil_id " +
                "WHERE u.username = ? and u.actif != -1";
//                "INNER JOIN users u ON ur.user_id = u.user_id " +

        List<String> strings = jdbcTemplate.queryForList(sql, new String[]{s}, String.class);

        authorities.addAll(
                strings.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                        .collect(Collectors.toList())
        );

        userDetails.setUser(user);
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
