package com.api.blog_api.repository;

import com.api.blog_api.model.PostModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository repository;

    @Test
    void deveSalvarERetornarPost(){
        //Arrange
        PostModel post = new PostModel(
                "Giovanni",
                "Aulas",
                "A semana toda têm aulas");

        //Act
        PostModel postSalvo = repository.save(post);

        //Assert
        Assertions.assertThat(postSalvo).isNotNull();
    }

    @Test
    void deveBuscarPostPeloId(){

        PostModel post = new PostModel(
                "Giovanni",
                "Aulas",
                "Aulas");

        PostModel postSalvo = repository.save(post);

        PostModel postBuscar = repository.findById(postSalvo.getId()).get();
        Assertions.assertThat(postBuscar).isNotNull();
        Assertions.assertThat(postBuscar.getId()).isEqualTo(postSalvo.getId());
    }

    @Test
    void deveListarPosts(){
        PostModel post1 = new PostModel(
                "Giovanni",
                "Aulas",
                "texto1");
        PostModel post2 = new PostModel(
                "Giovanni",
                "Aulas",
                "texto2");

        repository.save(post1);
        repository.save(post2);

        var posts = repository.findAll();
        Assertions.assertThat(posts).hasSize(2);

    }

    @Test
    void deveBuscarPostsPeloTituloIgnorandoMaiusculasEMinusculas(){
        PostModel post  = new PostModel(
                "Giovanni",
                "Aulas",
                "texto");
        PostModel post1 = new PostModel(
                "Giovanni",
                "Aulas",
                "texto1");
        PostModel post2 = new PostModel(
                "Giovanni",
                "Aulas",
                "texto2");

        repository.save(post);
        repository.save(post1);
        repository.save(post2);

        Page<PostModel> resultado = repository.findByTituloContainingIgnoreCase(
                "aulas", PageRequest.of(0, 10));

        Assertions.assertThat(resultado.getContent()).hasSize(3);
        Assertions.assertThat(resultado.getContent().get(0).getTitulo()).isEqualTo("Aulas");
    }

}
