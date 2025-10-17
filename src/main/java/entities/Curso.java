package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Curso {
    @Id @GeneratedValue
    private Long id;

    private String nome;
    private String sigla;

    @ManyToOne
    private Professor professor;

    @OneToMany(mappedBy = "curso")
    private List<MaterialCurso> materiais;

    @OneToMany(mappedBy = "curso")
    private List<Aluno> alunos;

    public Curso() {}

    public Curso(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<MaterialCurso> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<MaterialCurso> materiais) {
        this.materiais = materiais;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
