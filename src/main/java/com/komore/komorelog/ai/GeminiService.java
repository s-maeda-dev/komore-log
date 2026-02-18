package com.komore.komorelog.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Google Gemini API との連携を担当するサービスクラス。
 */
@Service
@RequiredArgsConstructor
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * ユーザーの投稿内容に対して、Gemini API を使用して共感的な返信を生成します。
     */
    @SuppressWarnings("unchecked")
    public String getEmpathyReply(String userContent) {
        String fullUrl = apiUrl + apiKey;

        // 指示をコンテンツの冒頭に結合
        // ユーザーの要望に合わせて「100文字以内」という制約を強化しました
        String instruction = "【設定: あなたは「こもれ」という名前の、心優しい癒やし系のパートナーです。】\n" +
                "ユーザーのつぶやきに対し、親身になって優しく共感し、心がふっと軽くなるような言葉を返してください。\n" +
                "★大切にすること:\n" +
                "- 固すぎるカウンセラー口調でも、砕けすぎた友人口調でもなく、相手を敬いつつも心の距離が近い「丁寧であたたかい口調」で話してください。\n" +
                "- 「〜ですね」「〜ですよ」「〜でしょうか？」といった、丁寧で柔らかな語尾を基本にしてください。\n" +
                "★制約事項（厳守）:\n" +
                "1. 返信は **2〜3文ごとに改行** してください。\n" +
                "2. 改行の間に **空行は入れない** でください（詰めて表示）。\n" +
                "3. 返信の長さは、ユーザーの投稿内容に応じて柔軟に調整してください（短いつぶやきには簡潔に、深い悩みや相談には最大150文字程度まで広げて丁寧に）。\n" +
                "4. 悩みへのアドバイスも、上記の丁寧で親しみやすい形式を守って伝えてください。\n\n" +
                "--- ユーザーの投稿 ---\n" +
                userContent;

        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> userPart = new HashMap<>();
        userPart.put("text", instruction);
        Map<String, Object> contentMap = new HashMap<>();
        contentMap.put("parts", Collections.singletonList(userPart));
        requestBody.put("contents", Collections.singletonList(contentMap));

        try {
            Map<String, Object> response = restTemplate.postForObject(fullUrl, requestBody, Map.class);

            if (response != null && response.containsKey("candidates")) {
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
                if (!candidates.isEmpty()) {
                    Map<String, Object> candidate = candidates.get(0);
                    Map<String, Object> content = (Map<String, Object>) candidate.get("content");
                    List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                    if (!parts.isEmpty()) {
                        return (String) parts.get(0).get("text");
                    }
                }
            }
            return "（AIは優しく微笑んでいます。今は少しお疲れのようですが、あなたの言葉は届いていますよ。）";
        } catch (Exception e) {
            e.printStackTrace();
            return "（AIは今、あなたの言葉を心に刻んでいます。またあとでお話ししましょうね。）";
        }
    }
}
