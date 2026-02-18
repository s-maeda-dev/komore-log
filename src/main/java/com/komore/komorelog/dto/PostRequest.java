package com.komore.komorelog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 投稿リクエストを受けるための DTO。
 * バリデーションルールを定義します。
 */
@Data
public class PostRequest {

    /**
     * 投稿内容。
     * 空文字を禁止し、最大300文字までに制限します。
     */
    @NotBlank(message = "投稿内容は必須です。")
    @Size(max = 300, message = "投稿内容は300文字以内で入力してください。")
    private String content;
}
