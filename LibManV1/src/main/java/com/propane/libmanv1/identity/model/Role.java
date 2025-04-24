package com.propane.libmanv1.identity.model;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity @Table(name="role")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Role implements GrantedAuthority {
    @Id @GeneratedValue
    @Column(name="role_id")
    private Long id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
