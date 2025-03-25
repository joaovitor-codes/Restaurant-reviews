package br.com.facol.model;

import java.util.List;

public class User {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private List<String> telefones;

    public User() {
    }

    public User( String senha, String email, String nome) {
        this.senha = senha;
        this.email = email;
        this.nome = nome;
    }

    public int getUser_id() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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


}
