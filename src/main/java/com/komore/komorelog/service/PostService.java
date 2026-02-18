package com.komore.komorelog.service;

import com.komore.komorelog.ai.GeminiService;
import com.komore.komorelog.entity.Post;
import com.komore.komorelog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 投稿に関する業務ロジックを担当するクラス。
 */
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final GeminiService geminiService;

    /**
     * すべての投稿を新しい順に取得します。
     */
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    /**
     * 新しい投稿を保存します。
     * この段階では AI 返信はまだ生成しません（フロントエンドで別送するため）。
     */
    @Transactional
    public Post savePost(String content) {
        Post post = new Post();
        post.setContent(content);
        post.setAiReply("AIが考え中..."); // 初期状態
        return postRepository.save(post);
    }

    /**
     * 指定した投稿 ID に対して、Gemini API を使ってお返事を生成・保存します。
     */
    @Transactional
    public Post generateAiReply(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("投稿が見つかりませんでした。"));

        String aiReply = geminiService.getEmpathyReply(post.getContent());
        post.setAiReply(aiReply);

        return postRepository.save(post);
    }

    /**
     * 指定した ID の投稿を削除します。
     */
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
