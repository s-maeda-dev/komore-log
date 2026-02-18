package com.komore.komorelog.controller;

import com.komore.komorelog.entity.Post;
import com.komore.komorelog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * 投稿に関するリクエスト（API）を受け取るためのコントローラー。
 */
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 投稿一覧を JSON 形式で返します。
     * GET http://localhost:8080/api/posts
     */
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    /**
     * 新しい投稿を保存します。
     * POST http://localhost:8080/api/posts
     * Body: { "content": "つぶやき内容" }
     */
    @PostMapping
    public Post createPost(@RequestBody Map<String, String> request) {
        String content = request.get("content");
        return postService.savePost(content);
    }

    /**
     * 指定した投稿を削除します。
     * DELETE http://localhost:8080/api/posts/1
     */
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
