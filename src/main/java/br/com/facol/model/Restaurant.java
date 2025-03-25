package br.com.facol.model;

import br.com.facol.model.ENUM.Review;

import java.util.List;



public class Restaurant {
    private int id;
    private Review tag;
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

    public Review getTag() {
        return tag;
    }

    public void setTag(Review tag) {
        this.tag = tag;
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

    public void defTag(Restaurant restaurant, double media){
        try {
            if (media < 7) {
                restaurant.tag = Review.RUIM;
            } else if (media <= 7) {
                restaurant.tag = Review.BOM;
            }else if (media > 7 && media < 8) {
                restaurant.tag = Review.MUITO_BOM;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
