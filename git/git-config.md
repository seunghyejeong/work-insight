username password 계속 인증 피하기



1. token 만들기

2. config 설정 

   `git config --global user.name 'jeongbami'`

   `git config --global user.password '${token}'`

3.  config 저장

   `git config --global credential.helper store`

