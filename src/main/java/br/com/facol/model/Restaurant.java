package br.com.facol.model;

import java.util.List;

public class Restaurant {
    private int id;
    private Enum tag;
    private String nome;
    private String cidade;
    private String bairro;
    private String cep;
    private String email;
    private List<String> telefones;

    public Restaurant() {
    }

    public Restaurant(int id, String cep, String email, String bairro, String cidade, String nome) {
        this.id = id;
        this.cep = cep;
        this.email = email;
        this.bairro = bairro;
        this.cidade = cidade;
        this.nome = nome;
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

    public void getTelefones() {
        for (int i = 0; i < telefones.size(); i++) {
            System.out.println(telefones.get(i));
        }
    }

    public void addTelefone(String telefone) {
        try {
            this.telefones.add(telefone);
            System.out.println("Novo telefone adicionado: " + telefone);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Enum getTag() {
        return tag;
    }

    public void setTag(Enum tag) {
        this.tag = tag;
    }
}
