package br.com.taina.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 7)
    private String nome;

    public Role() {
    }

    public Role(Long id, String nome) {
        Id = id;
        this.nome = nome;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Role{" +
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Role role)) return false;
        return Objects.equals(getId(), role.getId()) && Objects.equals(getNome(), role.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome());
    }

    public enum Values{

        ADMIN(1L),

        BASIC(2L);

        final long roleId;

        public long getRoleId() {
            return roleId;
        }

        Values(long roleId) {
            this.roleId = roleId;
        }
    }
}
