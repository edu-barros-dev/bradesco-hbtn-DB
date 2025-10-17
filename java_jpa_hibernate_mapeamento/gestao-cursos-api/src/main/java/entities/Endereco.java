package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Endereco {
    @Id
    @GeneratedValue
    private Long id;

    private String logradouro;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer cep;

    @OneToMany(mappedBy = "endereco")
    private List<Aluno> alunos;

    public Endereco() {}

    public Endereco(String logradouro, String endereco, String numero, String bairro, String cidade, String estado, Integer cep) {
        this.logradouro = logradouro;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }
}
