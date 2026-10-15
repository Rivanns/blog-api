package com.api.blog_api.controller;

import com.api.blog_api.dto.request.ComentarioRequestDto;
import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.ComentarioResponseDto;
import com.api.blog_api.dto.response.PostResponseDto;
import com.api.blog_api.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.SeparatorUI;
import java.util.List;
import java.util.UUID;

@Tag(name = "Posts e comentários", description = "Operações do Blog API")
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    //LISTA TODOS OS POSTS
    @Operation(summary = "Lista posts com paginação")
    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDto>> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(defaultValue = "data") String sortBy,
                                                             @RequestParam(defaultValue = "desc") String direction,
                                                             @RequestParam(required = false) String titulo){

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
//        return ResponseEntity.ok(postService.findAll(pageable));
        return ResponseEntity.ok(postService.findAll(pageable, titulo));
    }

    //RETORNA UM POST INDIVIDUAL
    @Operation(summary = "Retorna um post pelo ID selecionado")
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable UUID id){
        return ResponseEntity.ok(postService.findById(id));
    }

    //CRIA UM POST
    @Operation(summary = "Cria um novo post")
    @PostMapping("/newpost")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostRequestDto dto){
        PostResponseDto created = postService.createPost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //ATUALIZA UM POST
    @Operation(summary = "Atualiza um post")
    @PutMapping("/atualizapost/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable UUID id, @RequestBody @Valid PostRequestDto dto){
        PostResponseDto updated = postService.updatePost(id, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);

    }

    //DELETA UM POST
    @Operation(summary = "Deleta um post")
    @DeleteMapping("/deletarpost/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable UUID id){
        Object deleted = postService;
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deleted);
    }

    //CRIA UM COMENTÁRIO
    @Operation(summary = "Adiciona um comentário em um post selecionado pelo ID")
    @PostMapping("/comentarios/{postId}")
    public ResponseEntity<ComentarioResponseDto> createComentario(@PathVariable UUID postId,
                                                               @RequestBody @Valid ComentarioRequestDto dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(postService.addComentario(postId, dto));
    }

    //ATUALIZA UM COMENTÁRIO
    @Operation(summary = "Atualiza um comentário")
    @PostMapping("/atualizarcomentario/{comentarioId}")
    public ResponseEntity<ComentarioResponseDto> updateComentario(@PathVariable UUID comentarioId,
                                                                  @RequestBody @Valid ComentarioRequestDto dto){
        ComentarioResponseDto comentarioUpdated = postService.updateComentario(comentarioId, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comentarioUpdated);
    }

    //DELETA UM COMENTÁRIO
    @Operation(summary = "Deleta um comentário")
    @DeleteMapping("/deletarcomentario/{comentarioId}")
    public ResponseEntity<Object> deleteComentario(@PathVariable UUID comentarioId){
        Object comentarioDeleted = postService;
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(comentarioDeleted);
    }

}
