package com.api.blog_api.service;

import com.api.blog_api.dto.request.ComentarioRequestDto;
import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.ComentarioResponseDto;
import com.api.blog_api.dto.response.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface PostService {
    //List<PostResponseDto> findAll();
    Page<PostResponseDto> findAll(Pageable pageable);
    PostResponseDto findById(UUID id);
    PostResponseDto createPost(PostRequestDto dto);
    Page<PostResponseDto> findAll(Pageable pageable, String titulo);
    ComentarioResponseDto addComentario(UUID id, ComentarioRequestDto dto);
    PostResponseDto updatePost (UUID id, PostRequestDto dto);
    void deletePost (UUID id);
    void deleteComentario (UUID comentarioId);
    ComentarioResponseDto updateComentario(UUID comentarioId, ComentarioRequestDto dto);
}
