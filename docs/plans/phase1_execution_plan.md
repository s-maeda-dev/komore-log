# 📋 Phase 1 実行計画書：データベース・基盤の準備

このドキュメントは、`komore-log` 開発の最初のステップである「Phase 1」の具体的な作業手順をまとめたものです。

---

## 1. 概要
アプリケーションがデータを保存し、正しく動作するための「土台」を構築します。

---

## 2. 詳細手順

### 2.1 MySQL データベースの作成
Javaプログラム（Spring Boot）から接続するための「箱」を MySQL の中に作成します。

1.  **MySQL への接続**：
    *   コマンドプロンプトや MySQL Workbench を使用して接続。
2.  **データベース（スキーマ）の作成**：
    *   `CREATE DATABASE komore_db CHARACTER SET utf8mb4;` を実行。
3.  **確認**：
    *   `SHOW DATABASES;` を実行し、`komore_db` が存在することを確認する。

### 2.2 アプリケーション設定の更新 (`application.properties`)
Spring Boot が作成したデータベースを見つけられるように、接続情報を書き込みます。

*   **ファイルパス**: `src/main/resources/application.properties`
*   **記述内容**:
    *   JDBC URL (`jdbc:mysql://localhost:3306/komore_db`)
    *   ユーザー名 (`root`)
    *   パスワード (設定したもの)
    *   Hibernate の自動テーブル作成設定 (`spring.jpa.hibernate.ddl-auto=update`)
        *   **初心者解説**: これを設定しておくと、Javaのコードを書くだけでデータベースのテーブルを自動で作ってくれるようになります！

### 2.3 エンティティ（Postクラス）の作成
データベースの `posts` テーブルに対応する Java のクラスを作成します。

*   **クラス名**: `Post.java`
*   **パッケージ**: `com.komore.komorelog.entity`
*   **項目（フィールド）**:
    *   `id`: 投稿を識別する自動採番の番号
    *   `content`: ユーザーのつぶやき（テキスト）
    *   `aiReply`: AIからの返信（テキスト）
    *   `createdAt`: 投稿されたタイミングの日時
*   **使用する技術**:
    *   `JPA (@Entity, @Id, @GeneratedValue)`: クラスをテーブルとして扱うための目印
    *   `Lombok (@Data)`: 面倒なコードを書かなくて済む魔法の呪文

---

## 3. 完了条件
- [x] `komore_db` が MySQL 内に作成されている。
- [x] プロジェクトを起動した際、データベース接続エラーが出ない。（設定済み）
- [x] `Post` エンティティが読み込まれ、自動的に `posts` テーブルの形が（将来的に）作成可能な状態になっている。

---

## 4. 次のステップ
Phase 1 が完了したら、実際にデータを保存する仕組みを作る「Phase 2: バックエンド編」に進みます。
