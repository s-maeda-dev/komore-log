# 🌿 こもろぐ (komore-log)

「こもろぐ」は、日々の何気ないつぶやきを優しく受け止める、癒やし系のマイクロブログサービスです。  
木漏れ日のような温かさを持つAIパートナー **「komore」** が、あなたの投稿にそっと寄り添い、共感のお返事を届けます。

## ✨ 特徴
- **シンプルな投稿**: 今の気持ちを、そのまま文字にして届けるだけ。
- **AIパートナー「komore」**: 否定せず、あなたの心に寄り添う丁寧なお返事を返します。
- **落ち着いたデザイン**: 「くすみブラウン」を基調とした、目に優しくリラックスできるビジュアル。
- **非同期の癒やし**: 投稿すると、komore が少し考えてからお返事をくれます。

## 🛠 技術スタック
- **Backend**: Java 17 / Spring Boot 3.4.2
- **Frontend**: HTML5 / Vanilla JavaScript / Tailwind CSS (via CDN)
- **Database**: H2 Database (In-Memory)
- **AI API**: Google Gemini 1.5 Flash

## 🚀 クイックスタート
### 1. API キーの準備
1. [Google AI Studio](https://aistudio.google.com/) から Gemini API キーを取得してください。
2. プロジェクトのルートディレクトリに `.env` ファイルを作成し、以下を記述します。
   ```env
   GEMINI_API_KEY=あなたのAPIキー
   ```

### 2. 起動
1. VS Code でプロジェクトを開きます。
2. `.vscode/launch.json` が設定されていることを確認してください（`.env` を読み込む設定が必要です）。
3. 実行ボタン（または F5）を押してアプリを起動します。
4. ブラウザで `http://localhost:8080` にアクセスします。

## 📁 プロジェクト構造
- `src/main/java`: バックエンドのメインロジック（Spring Boot）
- `src/main/resources/templates/index.html`: フロントエンドの画面定義
- `docs/`: 要件定義、設計図、各フェーズの実行計画書

## 📄 ライセンス
This project is licensed under the MIT License.
