package com.example.petshop.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.petshop.entity.Post;
import com.example.petshop.payload.request.CreatePostRequest;

public interface PostService {
    
    List<Post> getListPost();

    List<Post> getNewestPost(long id);

    Post getPost(long id);

    Post savePost(CreatePostRequest request,MultipartFile file) throws IOException;
}
