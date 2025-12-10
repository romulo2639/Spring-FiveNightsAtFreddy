package br.ifpe.jaboatao.FiveNightsAtFreddy.model;

public class Funcionario {

    private String funcoa;
    private Long id;
    private String nome;
    private String senha;
    private String dataDeNascimento;
    private String CPF;
    private String emailPessoal;
    private String emailEmpresarial;
    private int salario;

    public Funcionario(String funcao, Long id, String nome, String senha, String dataDeNascimento, String CPF, String emailPessoal, String emailEmpresarial, int salario) {
        
        this.funcoa = funcao;
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.dataDeNascimento = dataDeNascimento;
        this.CPF = CPF;
        this.emailPessoal = emailPessoal;
        this.emailEmpresarial = emailEmpresarial;
        this.salario = salario;

    }

    public String getFuncao() {
        return this.funcoa;
    }

    public void setFuncao(String funcao) {
        this.funcoa = funcao;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataDeNascimento() {
        return this.dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCPF() {
        return this.CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmailPessoal() {
        return this.emailPessoal;
    }

    public void setEmailPessoal(String emailPessoal) {
        this.emailPessoal = emailPessoal;
    }

    public String getEmailEmpresarial() {
        return this.emailEmpresarial;
    }

    public void setEmailEmpresarial(String emailEmpresarial) {
        this.emailEmpresarial = emailEmpresarial;
    }

    public int getSalario() {
        return this.salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

}
