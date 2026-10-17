package com.api.blog_api.repository;

import com.api.blog_api.model.PostModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
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

}
