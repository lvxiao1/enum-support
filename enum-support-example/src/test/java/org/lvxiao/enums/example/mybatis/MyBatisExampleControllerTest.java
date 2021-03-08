package org.lvxiao.enums.example.mybatis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.lvxiao.enums.example.entity.SexType;
import org.lvxiao.enums.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
public class MyBatisExampleControllerTest {

    @Autowired
    WebTestClient webClient;

    @Test
    public void selectTest() {
        User user = webClient.get().uri("/example/mybatis/select?id={id}", 1)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        assertThat(user).hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("sex", SexType.MAN);

        user = webClient.get().uri("/example/mybatis/select?id={id}", 2)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        assertThat(user).hasFieldOrPropertyWithValue("id", 2)
                .hasFieldOrPropertyWithValue("sex", SexType.WOMAN);
    }

    @Test
    public void selectBySexTest() {
        User user = webClient.get().uri("/example/mybatis/selectBySex?sexType={sexType}", 1)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        assertThat(user).hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("sex", SexType.MAN);

        user = webClient.get().uri("/example/mybatis/selectBySex?sexType={sexType}", 2)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();
        assertThat(user).hasFieldOrPropertyWithValue("id", 2)
                .hasFieldOrPropertyWithValue("sex", SexType.WOMAN);
    }

    @Test
    public void updateTest() {
        webClient.post()
                .uri("/example/mybatis/update")
                .syncBody(new User(1, SexType.WOMAN))
                .exchange()
                .expectStatus()
                .isOk().expectBody(String.class).isEqualTo("ok");

        User user = webClient.get().uri("/example/mybatis/select?id={id}", 1)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        assertThat(user).hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("sex", SexType.WOMAN);

        webClient.post()
                .uri("/example/mybatis/update")
                .syncBody(new User(1, SexType.MAN))
                .exchange()
                .expectStatus()
                .isOk().expectBody(String.class).isEqualTo("ok");
    }

}
