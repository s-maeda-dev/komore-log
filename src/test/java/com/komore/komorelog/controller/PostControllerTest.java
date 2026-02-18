package com.komore.komorelog.controller;

import com.komore.komorelog.entity.Post;
import com.komore.komorelog.service.PostService;
import com.komore.komorelog.dto.PostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("正常な投稿：300文字以内のコンテンツは保存される")
    void createPost_ShouldReturnSavedPost_WhenContentIsWithin300Chars() throws Exception {
        // Preparation
        PostRequest request = new PostRequest();
        request.setContent("こんにちは、今日の気分はとても良いです。");

        Post savedPost = new Post();
        savedPost.setId(1L);
        savedPost.setContent(request.getContent());
        savedPost.setAiReply("（AIの返信）");

        when(postService.savePost(anyString())).thenReturn(savedPost);

        // Execution & Verification
        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(request.getContent()))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("バリデーションエラー：コンテンツが空の場合は400エラーが返る")
    void createPost_ShouldReturn400_WhenContentIsBlank() throws Exception {
        // Preparation
        PostRequest request = new PostRequest();
        request.setContent(""); // 空文字

        // Execution & Verification
        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.content").value("投稿内容は必須です。"));
    }

    @Test
    @DisplayName("バリデーションエラー：コンテンツが300文字を超える場合は400エラーが返る")
    void createPost_ShouldReturn400_WhenContentExceeds300Chars() throws Exception {
        // Preparation
        PostRequest request = new PostRequest();
        request.setContent("あ".repeat(301)); // 301文字

        // Execution & Verification
        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.content").value("投稿内容は300文字以内で入力してください。"));
    }
}
