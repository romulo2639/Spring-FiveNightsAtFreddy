package br.ifpe.jaboatao.FiveNightsAtFreddy.DAO;

import br.ifpe.jaboatao.FiveNightsAtFreddy.model.Animatronic;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AnimatronicDAO {

    private final JdbcClient jdbcClient;

    public AnimatronicDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    // =========================
    // CREATE
    // =========================
    public Animatronic create(Animatronic anim) {

        String sql = """
            INSERT INTO animatronic (
                nome, animal, cor, dataDeFabricacao, instrumento, funcao
            ) VALUES (
                :nome, :animal, :cor, :dataDeFabricacao, :instrumento, :funcao
            )
        """;

        jdbcClient.sql(sql)
                .param("nome", anim.getNome())
                .param("animal", anim.getAnimal())
                .param("cor", anim.getCor())
                .param("dataDeFabricacao", anim.getDataDeFabricacao())
                .param("instrumento", anim.getInstrumento())
                .param("funcao", anim.getFuncao())
                .update();

        Long id = jdbcClient.sql("SELECT LAST_INSERT_ID()")
                .query(Long.class)
                .single();

        anim.setId(id);
        return anim;
    }


    // =========================
    // READ (by ID)
    // =========================
    public Optional<Animatronic> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM animatronic WHERE id = :id")
                .param("id", id)
                .query(Animatronic.class)
                .optional();
    }


    // =========================
    // READ (all)
    // =========================
    public List<Animatronic> findAll() {
        return jdbcClient.sql("SELECT * FROM animatronic")
                .query(Animatronic.class)
                .list();
    }


    // =========================
    // READ (filters)
    // =========================
    public List<Animatronic> findByFilters(
            String nome,
            String animal,
            String cor,
            String instrumento,
            String funcao
    ) {
        StringBuilder sql = new StringBuilder("SELECT * FROM animatronic WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (nome != null && !nome.isBlank()) {
            sql.append(" AND nome LIKE :nome ");
            params.put("nome", "%" + nome + "%");
        }

        if (animal != null && !animal.isBlank()) {
            sql.append(" AND animal LIKE :animal ");
            params.put("animal", "%" + animal + "%");
        }

        if (cor != null && !cor.isBlank()) {
            sql.append(" AND cor LIKE :cor ");
            params.put("cor", "%" + cor + "%");
        }

        if (instrumento != null && !instrumento.isBlank()) {
            sql.append(" AND instrumento LIKE :instrumento ");
            params.put("instrumento", "%" + instrumento + "%");
        }

        if (funcao != null && !funcao.isBlank()) {
            sql.append(" AND funcao LIKE :funcao ");
            params.put("funcao", "%" + funcao + "%");
        }

        return jdbcClient.sql(sql.toString())
                .params(params)
                .query(Animatronic.class)
                .list();
    }


    // =========================
    // UPDATE
    // =========================
    public int update(Long id, Animatronic anim) {
        String sql = """
            UPDATE animatronic
            SET nome = :nome,
                animal = :animal,
                cor = :cor,
                dataDeFabricacao = :dataDeFabricacao,
                instrumento = :instrumento,
                funcao = :funcao
            WHERE id = :id
        """;

        return jdbcClient.sql(sql)
                .param("nome", anim.getNome())
                .param("animal", anim.getAnimal())
                .param("cor", anim.getCor())
                .param("dataDeFabricacao", anim.getDataDeFabricacao())
                .param("instrumento", anim.getInstrumento())
                .param("funcao", anim.getFuncao())
                .param("id", id)
                .update();
    }


    // =========================
    // DELETE
    // =========================
    public int delete(Long id) {
        return jdbcClient.sql("DELETE FROM animatronic WHERE id = :id")
                .param("id", id)
                .update();
    }

}
