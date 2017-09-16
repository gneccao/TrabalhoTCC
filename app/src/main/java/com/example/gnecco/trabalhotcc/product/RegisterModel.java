package com.example.gnecco.trabalhotcc.product;


public class RegisterModel {

    private Integer Id;
    private String Modelo;
    private String Valor;
    private String Ano;

    public RegisterModel(int id, String modelo, String valor, String ano) {
        this.Id = id;
        this.Modelo = modelo;
        this.Valor = valor;
        this.Ano = ano;
    }
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getAno() {
        return Ano;
    }

    public void setAno(String ano) {
        Ano = ano;
    }
    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }


}
