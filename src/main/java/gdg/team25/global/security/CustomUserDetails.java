package gdg.team25.global.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@NoArgsConstructor
@ToString
public class CustomUserDetails implements UserDetails {
    private String userId;
    private String email;
    private Collection<? extends GrantedAuthority> roles;
    private String password;

    private CustomUserDetails(String userId, String email, Collection<? extends GrantedAuthority> role, String password) {
        this.userId = userId;
        this.email = email;
        this.roles = role;
        this.password = password;
    }

    public static CustomUserDetails of(String id, String email, Collection<? extends GrantedAuthority> role, String password) {
        return new CustomUserDetails(id, email, role, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
