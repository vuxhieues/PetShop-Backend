package com.example.petshop.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.petshop.entity.Post;
import com.example.petshop.payload.request.CreatePostRequest;
import com.example.petshop.service.PostService;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/blog")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public ResponseEntity<?> getListPost(){
        List<Post> listPost = postService.getListPost();

        return ResponseEntity.ok(listPost);
    }


    @GetMapping("/newest/{id}")
    public ResponseEntity<?> getNewestPost(@PathVariable long id){
        List<Post> listPost = postService.getNewestPost(id);

        return ResponseEntity.ok(listPost);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@ModelAttribute CreatePostRequest request,@RequestParam("file") MultipartFile file) throws IOException{
        Post post = postService.savePost(request, file);
        return ResponseEntity.ok(post);
    }
}
