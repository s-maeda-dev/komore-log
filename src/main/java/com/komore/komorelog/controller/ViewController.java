package com.komore.komorelog.controller;

import com.komore.komorelog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HTML 画面を表示するためのコントローラー。
 */
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final PostService postService;

    /**
     * トップページ（投稿一覧画面）を表示します。
     */
    @GetMapping("/")
    public String index(Model model) {
        // 全投稿を取得して画面に渡す
        model.addAttribute("posts", postService.getAllPosts());
        // templates/index.html を呼び出す
        return "index";
    }
}
