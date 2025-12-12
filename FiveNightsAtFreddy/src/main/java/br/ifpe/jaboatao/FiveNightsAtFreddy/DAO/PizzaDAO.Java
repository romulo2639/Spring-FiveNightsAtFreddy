package br.ifpe.jaboatao.FiveNightsAtFreddy.DAO;

import br.ifpe.jaboatao.FiveNightsAtFreddy.model.Pizza;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PizzaDAO {

    private final JdbcClient jdbcClient;

    public PizzaDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // =========================================
    // CREATE
    // =========================================
    public Pizza create(Pizza pizza) {

        String sql = """
            INSERT INTO pizza (
                nome, preco, ing1, ing2, ing3
            ) VALUES (
                :nome, :preco, :ing1, :ing2, :ing3
            )
        """;

        jdbcClient.sql(sql)
                .param("nome", pizza.getNome())
                .param("preco", pizza.getPreco())
                .param("ing1", pizza.getIng1())
                .param("ing2", pizza.getIng2())
                .param("ing3", pizza.getIng3())
                .update();

        Long id = jdbcClient.sql("SELECT LAST_INSERT_ID()")
                .query(Long.class)
                .single();

        // Se quiser adicionar id à classe Pizza, basta colocar um setId aqui
        return pizza;
    }


    // =========================================
    // READ BY ID
    // =========================================
    public Optional<Pizza> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM pizza WHERE id = :id")
                .param("id", id)
                .query(Pizza.class)
                .optional();
    }

    // =========================================
    // READ ALL
    // =========================================
    public List<Pizza> findAll() {
        return jdbcClient.sql("SELECT * FROM pizza")
                .query(Pizza.class)
                .list();
    }

    // =========================================
    // READ WITH FILTERS
    // =========================================
    public List<Pizza> findByFilters(
            String nome,
            Integer precoMin,
            Integer precoMax,
            String ingrediente
    ) {
        StringBuilder sql = new StringBuilder("SELECT * FROM pizza WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (nome != null && !nome.isBlank()) {
            sql.append(" AND nome LIKE :nome ");
            params.put("nome", "%" + nome + "%");
        }

        if (precoMin != null) {
            sql.append(" AND preco >= :precoMin ");
            params.put("precoMin", precoMin);
        }

        if (precoMax != null) {
            sql.append(" AND preco <= :precoMax ");
            params.put("precoMax", precoMax);
        }

        if (ingrediente != null && !ingrediente.isBlank()) {
            sql.append("""
                AND (
                    ing1 LIKE :ingrediente OR
                    ing2 LIKE :ingrediente OR
                    ing3 LIKE :ingrediente
                )
            """);
            params.put("ingrediente", "%" + ingrediente + "%");
        }

        return jdbcClient.sql(sql.toString())
                .params(params)
                .query(Pizza.class)
                .list();
    }


    // =========================================
    // UPDATE
    // =========================================
    public int update(Long id, Pizza pizza) {

        String sql = """
            UPDATE pizza
            SET nome = :nome,
                preco = :preco,
                ing1 = :ing1,
                ing2 = :ing2,
                ing3 = :ing3
            WHERE id = :id
        """;

        return jdbcClient.sql(sql)
                .param("nome", pizza.getNome())
                .param("preco", pizza.getPreco())
                .param("ing1", pizza.getIng1())
                .param("ing2", pizza.getIng2())
                .param("ing3", pizza.getIng3())
                .param("id", id)
                .update();
    }

    // =========================================
    // DELETE
    // =========================================
    public int delete(Long id) {
        return jdbcClient.sql("DELETE FROM pizza WHERE id = :id")
                .param("id", id)
                .update();
    }
}
