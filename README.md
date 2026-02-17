# komore-log
心に寄り添うつぶやきアプリ「komore（こもれ）」のMVP開発。Java/Spring BootとOpenAI APIを活用し、ユーザーの孤独を和らげる最小機能を実装。

# 🌳 komore-log (こもれログ)
**「今の気持ちをそっと置く、AI共感型のつぶやき日記」**

「友達に話すほどじゃないけれど、誰かに聞いてほしい」
そんな何気ないつぶやきをタイムラインに刻む場所です。
AIがあなたのつぶやきを丸ごと受け止め、木漏れ日のような穏やかな返信を届けます。

## ✨ MVP機能
- **つぶやき投稿**: 今の気持ちを短文でサッと記録（タイトル入力なし）
- **AI共感メッセージ**: OpenAI APIが、投稿内容に優しく寄り添う返信を生成
- **タイムライン閲覧**: 自分だけのタイムラインで、過去のつぶやきとAIの返答を振り返る

## 🛠️ 技術スタック
- **Backend**: Java 21 / Spring Boot 3.x
- **Frontend**: Tailwind CSS / Stitch (UI AI Agent)
- **Database**: MySQL
- **AI**: OpenAI API (GPT-4o)