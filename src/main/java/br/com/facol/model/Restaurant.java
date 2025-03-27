package br.com.facol.model;

public class Restaurant {
    private int id;
    private String CNPJ;
    private String nome;
    private String cidade;
    private String bairro;
    private String cep;
    private String email;

    public Restaurant() {
    }

    public Restaurant(int id, String cep, String email, String bairro, String cidade, String nome, String CNPJ) {
        this.id = id;
        this.cep = cep;
        this.email = email;
        this.bairro = bairro;
        this.cidade = cidade;
        this.nome = nome;
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

}
