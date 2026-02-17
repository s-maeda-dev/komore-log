package com.komore.komorelog.service;

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

    /**
     * すべての投稿を新しい順に取得します。
     */
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    /**
     * 新しい投稿を保存します。
     * 現在は AI 返信としてダミーテキストを設定します。
     */
    @Transactional
    public Post savePost(String content) {
        Post post = new Post();
        post.setContent(content);

        // Phase 3 で本物の AI と連携させます。今は仮の文字を入れます。
        post.setAiReply("（AIがあなたの言葉を優しく受け止めています...）");

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
