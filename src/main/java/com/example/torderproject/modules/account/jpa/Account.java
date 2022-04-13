package com.example.torderproject.modules.account.jpa;

import com.example.torderproject.modules.account.dto.RequestNewAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;


//    @OneToMany(mappedBy = "account")
//    private List<Menu> menus = new ArrayList<>();


    public Account(RequestNewAccount requestNewAccount) {
        this.username = requestNewAccount.getUsername();
        this.password = requestNewAccount.getPassword();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
