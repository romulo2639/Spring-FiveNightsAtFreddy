package br.ifpe.jaboatao.FiveNightsAtFreddy.DAO;

import br.ifpe.jaboatao.FiveNightsAtFreddy.model.Funcionario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FuncionarioDAO {

    private final JdbcClient jdbcClient;

    public FuncionarioDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // ========================================================
    // CREATE
    // ========================================================
    public Funcionario create(Funcionario funcionario) {

        String sql = """
            INSERT INTO funcionario (
                funcao, nome, senha, dataDeNascimento, cpf,
                emailPessoal, emailEmpresarial, salario
            ) VALUES (
                :funcao, :nome, :senha, :dataDeNascimento, :cpf,
                :emailPessoal, :emailEmpresarial, :salario
            )
        """;

        jdbcClient.sql(sql)
                .param("funcao", funcionario.getFuncao())
                .param("nome", funcionario.getNome())
                .param("senha", funcionario.getSenha())
                .param("dataDeNascimento", funcionario.getDataDeNascimento())
                .param("cpf", funcionario.getCPF())
                .param("emailPessoal", funcionario.getEmailPessoal())
                .param("emailEmpresarial", funcionario.getEmailEmpresarial())
                .param("salario", funcionario.getSalario())
                .update();

        Long id = jdbcClient.sql("SELECT LAST_INSERT_ID()").query(Long.class).single();

        funcionario.setId(id);
        return funcionario;
    }

    // ========================================================
    // READ (BY ID)
    // ========================================================
    public Optional<Funcionario> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM funcionario WHERE id = :id")
                .param("id", id)
                .query(Funcionario.class)
                .optional();
    }

    // ========================================================
    // READ (ALL)
    // ========================================================
    public List<Funcionario> findAll() {
        return jdbcClient.sql("SELECT * FROM funcionario")
                .query(Funcionario.class)
                .list();
    }

    // ========================================================
    // READ (FILTERS)
    // ========================================================
    public List<Funcionario> findByFilters(
            String nome,
            String cpf,
            String funcao,
            Integer salarioMin,
            Integer salarioMax
    ) {
        StringBuilder sql = new StringBuilder("SELECT * FROM funcionario WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (nome != null && !nome.isBlank()) {
            sql.append(" AND nome LIKE :nome ");
            params.put("nome", "%" + nome + "%");
        }

        if (cpf != null && !cpf.isBlank()) {
            sql.append(" AND cpf = :cpf ");
            params.put("cpf", cpf);
        }

        if (funcao != null && !funcao.isBlank()) {
            sql.append(" AND funcao LIKE :funcao ");
            params.put("funcao", "%" + funcao + "%");
        }

        if (salarioMin != null) {
            sql.append(" AND salario >= :salarioMin ");
            params.put("salarioMin", salarioMin);
        }

        if (salarioMax != null) {
            sql.append(" AND salario <= :salarioMax ");
            params.put("salarioMax", salarioMax);
        }

        return jdbcClient.sql(sql.toString())
                .params(params)
                .query(Funcionario.class)
                .list();
    }

    // ========================================================
    // UPDATE
    // ========================================================
    public int update(Long id, Funcionario funcionario) {

        String sql = """
            UPDATE funcionario
            SET funcao = :funcao,
                nome = :nome,
                senha = :senha,
                dataDeNascimento = :dataDeNascimento,
                cpf = :cpf,
                emailPessoal = :emailPessoal,
                emailEmpresarial = :emailEmpresarial,
                salario = :salario
            WHERE id = :id
        """;

        return jdbcClient.sql(sql)
                .param("funcao", funcionario.getFuncao())
                .param("nome", funcionario.getNome())
                .param("senha", funcionario.getSenha())
                .param("dataDeNascimento", funcionario.getDataDeNascimento())
                .param("cpf", funcionario.getCPF())
                .param("emailPessoal", funcionario.getEmailPessoal())
                .param("emailEmpresarial", funcionario.getEmailEmpresarial())
                .param("salario", funcionario.getSalario())
                .param("id", id)
                .update();
    }

    // ========================================================
    // DELETE
    // ========================================================
    public int delete(Long id) {
        return jdbcClient.sql("DELETE FROM funcionario WHERE id = :id")
                .param("id", id)
                .update();
    }
}
