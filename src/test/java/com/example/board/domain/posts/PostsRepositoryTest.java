package com.example.board.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("mrchoi@bssm.hs.kr")
                .build());

        List<Posts> postsList = postsRepository.findAll();
        //postsRepository.findAll().forEach(System.out::println);

        Posts posts = postsList.get(0);
        Assertions.assertEquals(posts.getTitle(), "테스트 게시글");
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        //LocalDateTime now = LocalDateTime.of(2022,2,19,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());
        //System.out.println(now);
    }
}