# ChatWebApplication  
Spring Boot、Thymeleaf、単体・結合テスト（JUnit）、UML の練習として作成しました。（未完成）  
  
復習としてJPA（Spring JPA）を  
新たな技術として、H2データベース、Spring Security、Bootstrapを使ってみました。  

## 要件定義
• ユーザは新規ユーザ登録ができる  
• ユーザは新規ユーザ登録後システムへログインできる  
• ユーザは新しいチャネルをシステムへ追加できる  
• ユーザは既存のチャネルを選択して選択したチャネルに入ることができる  
• ユーザは選択したチャネルへ発言できる  
• ユーザはシステムからログアウトできる  
  
## 設計
ユースケース図  
![ユースケース図0](https://user-images.githubusercontent.com/75174022/169300774-ca77adb3-8b97-4515-853b-ece498366ec4.png)
  
クラス図はこんな感じです。  
![クラス図](https://user-images.githubusercontent.com/75174022/169300837-ccecc15b-a6ba-4845-aa46-39b1f3347962.png)
  
EntityとかRepositoryとかのDBよりのクラスまでクラス図に書く必要あるのかどうなんでしょう。  
せめてEntityはER図とかにすべきだったような気もします。  
  
システム全体の方針は基本的なSpring MVCモデルに従ったつもりです。
Spring MVCモデル  
![MVC](https://user-images.githubusercontent.com/75174022/169303064-4ea3bf7e-d075-4456-b920-b170b9dfc044.png)
  
ViewにBootstrapを使いました。  

## 実装画面  
ログイン画面  
![2022-05-18](https://user-images.githubusercontent.com/75174022/169303825-4ce93ac7-ec5f-471f-bcb2-128fb342f66c.png)
  
ユーザ登録画面  
![3](https://user-images.githubusercontent.com/75174022/169303834-7d88ccde-16d8-4862-8850-d10d3eb74e47.png)
  
チャット画面  
![10](https://user-images.githubusercontent.com/75174022/169304005-5884b4fa-39e5-4185-ac9d-078d16296002.png)
