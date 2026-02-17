package com.komore.komorelog.repository;

import com.komore.komorelog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 投稿データを操作するためのインターフェース。
 * JpaRepository を継承することで、基本的な保存、取得、削除などのメソッドが自動的に利用可能になります。
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 作成日時の新しい順（降順）ですべての投稿を取得します。
     * Spring Data JPA の機能により、メソッド名から SQL が自動生成されます。
     */
    List<Post> findAllByOrderByCreatedAtDesc();
}
