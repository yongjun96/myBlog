package com.myblog.api.service;


import com.myblog.api.domain.Post;
import com.myblog.api.repository.PostRepository;
import com.myblog.api.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//롬복을 이용해서 넣어주는 것도 가능
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate){
        //repository.save(postCreate);

        // postCreate -> Entity
        Post post = new Post(postCreate.getTitle(), postCreate.getContent());

        postRepository.save(post);

    }
}
