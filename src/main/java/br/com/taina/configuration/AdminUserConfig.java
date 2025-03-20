package br.com.taina.configuration;

import br.com.taina.entity.Pessoa;
import br.com.taina.entity.Role;
import br.com.taina.repository.PessoaRepository;
import br.com.taina.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PessoaRepository pessoaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, PessoaRepository pessoaRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.pessoaRepository = pessoaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByNome(Role.Values.ADMIN.name());

        var userAdmin = pessoaRepository.findByEmail("taina@gmail.com");

        userAdmin.ifPresentOrElse(
                usuario -> System.out.println("Admin já existe!"),
                () -> {
                    var user = new Pessoa();
                    user.setNome("Taina");
                    user.setEmail("taina@gmail.com");
                    user.setSenha(passwordEncoder.encode("123456"));
                    user.setRoles(Set.of(roleAdmin));

                    pessoaRepository.save(user);
                    System.out.println("Admin criado com sucesso!");
                }
        );
    }
}
