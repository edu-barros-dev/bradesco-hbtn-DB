package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class GestaoCursosMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");

        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();

        // Criando professor
        Professor professor = new Professor("Joao Silva", "PROF123", "joao.silva@universidade.edu");

        // Criando endereço
        Endereco endereco = new Endereco("Rua das Flores", "Apto 101", "123", "Centro", "Osasco", "SP", 6000000);

        // Persistir professor e endereco primeiro
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(professor);
        em.persist(endereco);
        em.getTransaction().commit();
        em.close();

        // Criando curso e associando professor (professor já persistido)
        Curso curso = new Curso("Programação Orientada a Objetos", "POO");
        curso.setProfessor(professor);

        // Persistir curso via model (professor já gerenciado)
        cursoModel.create(curso);

        // Criando material do curso e definindo o lado dono
        MaterialCurso material = new MaterialCurso("https://material.poo.com/aula1.pdf");
        // obter referência gerenciada do curso e atribuir ao material
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Curso cursoRef = em.getReference(Curso.class, curso.getId());
        material.setCurso(cursoRef);
        em.persist(material);
        em.getTransaction().commit();
        em.close();

        // Criando aluno
        Aluno aluno = new Aluno(
                "Maria Oliveira",
                "ALU456",
                new Date(),
                "maria.oliveira@email.com"
        );

        // Associando aluno ao curso e ao endereco (lado dono: aluno.curso, aluno.endereco)
        aluno.setCurso(curso); // curso já persistido
        aluno.setEndereco(endereco);

        // Criando telefone e associando ao aluno (lado dono: telefone.aluno)
        Telefone telefone = new Telefone("11", "91234-5678");
        telefone.setAluno(aluno);
        // também manter a lista no aluno (útil para navegação)
        aluno.setTelefones(List.of(telefone));

        // Persistir aluno via model
        alunoModel.create(aluno);

        // Agora persistir telefone referenciando a entidade gerenciada do aluno
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Aluno alunoRef = em.getReference(Aluno.class, aluno.getId());
        telefone.setAluno(alunoRef);
        em.persist(telefone);
        em.getTransaction().commit();
        em.close();

        System.out.println("Dados populados com sucesso!");

        emf.close();
    }

}
