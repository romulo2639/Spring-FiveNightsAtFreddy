package br.ifpe.jaboatao.FiveNightsAtFreddy.model;

public class Animatronic {

    private Long id;
    private String nome;
    private String animal;
    private String cor;
    private String dataDeFabricacao;
    private String instrumento;
    private String funcao;



    public Animatronic(Long id, String nome, String animal, String cor, String dataDeFabricacao, String instrumento, String funcao) {
        
        this.id = id;
        this.nome = nome;
        this.animal = animal;
        this.cor = cor;
        this.dataDeFabricacao = dataDeFabricacao;
        this.instrumento = instrumento;
        this.funcao = funcao;

    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnimal() {
        return this.animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDataDeFabricacao() {
        return this.dataDeFabricacao;
    }

    public void setDataDeFabricacao(String dataDeFabricacao) {
        this.dataDeFabricacao = dataDeFabricacao;
    }

    public String getInstrumento() {
        return this.instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }
    
    public String getFuncao() {
        return this.funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }    

}