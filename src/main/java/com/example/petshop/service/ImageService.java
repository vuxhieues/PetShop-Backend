package com.example.petshop.service;

import java.util.List;

import com.example.petshop.entity.Image;

public interface ImageService {
    
    List<Image> getListImage();

    Image getImageById(long id);

    Image save(Image image);

    List<Image> getListByUser(long userId);

    void deleteImage(String uploadDir,String filename);

}
