package com.api.blog_api.repository;

import com.api.blog_api.model.PostModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
import java.util.List;

@DataJpaTest(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
public class PostRepositoryTest {

    @Autowired
    private PostRepository repository;

    @Test
    void deveSalvarERetornarPost(){
        //Arrange
        PostModel post = new PostModel("Giovanni", "Aulas", "A semana toda têm aulas");

        //Act
        PostModel postSalvo = repository.save(post);

        //Assert
        Assertions.assertThat(postSalvo).isNotNull();
    }

    @Test
    void deveBuscarPostPeloId(){

        PostModel post = new PostModel("Giovanni", "Aulas", "Aulas");

        PostModel postSalvo = repository.save(post);

        PostModel postBuscar = repository.findById(postSalvo.getId()).get();
        Assertions.assertThat(postBuscar).isNotNull();
        Assertions.assertThat(postBuscar.getId()).isEqualTo(postSalvo.getId());
    }

}
