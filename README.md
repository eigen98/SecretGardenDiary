# SecretGardenDiary   
비밀다이어리   
1.비밀번호를 저장하는 기능, 변경하는 기능
2. 다이어리 내용을 앱이 종료되더라도 기기에 저장하는 기능(예상치 못한 종료 삭제 예방)   

![image](https://user-images.githubusercontent.com/68258365/131245895-6ce266e4-fe51-442f-a474-c9b445e713f3.png)   

Layout그리기   
-> ConstraintLayout 사용   
-> Custom Font 사용   
   
Handler 사용 (mainThread(UI Thread)와 생성한 Thread연결해줄 필요 -> 핸들러이용)   
SharedPreference 사용   Cancel changes
AlertDialog사용    

#복습   
##sharedpreference이용시 함수getSharedPreferences이용('파일이름'과 '모드' 인자 필요)   
-> getString으로 가져오기(getInt, getFloat,...)   
-> 저장할 시 edit이용(commit과 apply방식)   
(commit은 ui를 블럭한 상태로 데이터 저장될 때까지 기다림(동기), apply는 비동기(무거운 작업할 시 이용))   

##Theme이용   
style추가해서 NoActionBar   

##AlertDialog   
빌더패턴 이용

##Handler 사용   
Thread와 Thread간의 통신을 엮어주는 안드로이드에서 제공하는 기능.   
MainThread가 느려질 수 있는 작업을 할 시 별도로 쓰레드를 열어서 작업을 하게 되는데 (네트워크 작업 5초뒤 업데이트 필요) 쓰레드에서 UI로 접근 필요시 작업 불가
(ex) handler.post()   
Activity클래스 안에 핸들러 존재   


