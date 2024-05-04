package com.example.petshop.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.petshop.entity.Post;
import com.example.petshop.exception.NotFoundException;
import com.example.petshop.payload.request.CreatePostRequest;
import com.example.petshop.repository.PostRepository;
import com.example.petshop.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getListPost() {
        // TODO Auto-generated method stub
        return postRepository.findAll();
    }

    @Override
    public List<Post> getNewestPost(long id) {
        // TODO Auto-generated method stub
        List<Post> listPost = postRepository.getListNewest(id);
        return listPost;
    }

    @Override
    public Post getPost(long id) {
        // TODO Auto-generated method stub
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found post with id: " + id));

        return post;
    }

    @Override
    public Post savePost(CreatePostRequest request,MultipartFile file) throws IOException {
        // TODO Auto-generated method stub
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setContent(request.getContent());
        post.setThumbnail(file.getBytes());
        post.setThumbnail(file.getBytes());
        postRepository.save(post);
        return post;
    }

   
    
}
