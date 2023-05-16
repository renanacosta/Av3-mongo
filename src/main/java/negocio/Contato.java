package negocio;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

public class Contato {
    private ObjectId id;
    private String nome;
    private List<String> telefone;
    private Endereco endereco;

    public Contato() {
    }

    public Contato(String nome, Endereco endereco) {
        this.nome = nome;
        this.telefone = new ArrayList<String>();
        this.endereco = endereco;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<String> telefone) {
        this.telefone = telefone;

    }

    public void addTelefone(String telefone) {
        this.telefone.add(telefone);
    }

    @Override
    public String toString() {
        return "{" + "\n" + "\n" +
                " id = " + id + ",\n" +
                " Nome: " + nome + ",\n" +
                " Telefone(s): " + telefone + ",\n" +
                " Cidade: " + endereco.getCidade() + ",\n" +
                " Bairro: " + endereco.getBairro() + ",\n" +
                " Rua: " + endereco.getRua() + ",\n" +
                " Numero: " + endereco.getNumero() + ",\n" +
                " Complemento: " + endereco.getComplemento() + ",\n" +
                "}";
    }
}
