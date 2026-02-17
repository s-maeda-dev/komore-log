package com.komore.komorelog.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 投稿データを表すクラス（Entity）。
 * データベースの 'post' テーブルと対応します。
 */
@Entity
@Table(name = "posts")
@Data
public class Post {

    /**
     * 投稿ID（自動採番）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 投稿内容（最大300文字想定）
     */
    @Column(nullable = false, length = 300)
    private String content;

    /**
     * AIからの返信内容
     */
    @Column(columnDefinition = "TEXT")
    private String aiReply;

    /**
     * 投稿日時
     */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /**
     * 保存前に日時を自動設定する
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
