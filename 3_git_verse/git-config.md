username password 계속 인증 피하기



1. token 만들기

2. config 설정 

   `git config --global user.name 'jeongbami'`

   `git config --global user.password '${token}'`

3.  config 저장

   `git config --global credential.helper store`

- git repository 재설정
```
git init
git remote set-url origin https://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@github.com/seunghyejeong/gitops-repository.git
git add .
git commit -m "커밋 메시지"
git push origin [브랜치 명]
```