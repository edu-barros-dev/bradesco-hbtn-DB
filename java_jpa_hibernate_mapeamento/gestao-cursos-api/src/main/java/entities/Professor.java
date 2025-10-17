package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Professor {
    @Id
    @GeneratedValue
    private Long id;

    private String nomeCompleto;
    private String matricula;
    private String email;

    @OneToMany(mappedBy = "professor")
    private List<Curso> cursos;

    public Professor() {}

    public Professor(String nomeCompleto, String matricula, String email) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.email = email;
    }
}
