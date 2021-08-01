# 게시판
### SpringBoot, MariaDB, JPA, spring security, BootStrap 사용

< DB>

회원관리와 게시글 관리를 위한 테이블은 총 네 가지를 사용합니다.

<img width="367" alt="스크린샷 2021-08-01 오후 7 54 06" src="https://user-images.githubusercontent.com/33304967/127768249-92d101b3-25dd-400b-ba89-a61a1746580f.png">

<메인화면>

- 초기 화면은 다음과 같습니다. 초기 화면의 템플릿은 BootStrap을 이용했습니다.

<img width="1439" alt="1" src="https://user-images.githubusercontent.com/33304967/127768175-64f64ee0-12e9-4d1b-a0d8-e4d6180484d0.png">

- 초기 화면을 제외한 기능을 로그인 없이 이용하려고 하면, 자동으로 로그인 화면으로 이동합니다.

<img width="1439" alt="2" src="https://user-images.githubusercontent.com/33304967/127768197-01ec6bdd-32b1-42c6-8974-e89150d53d92.png">

<회원가입>

<img width="583" alt="스크린샷 2021-08-01 오후 7 58 25" src="https://user-images.githubusercontent.com/33304967/127768352-af1e73ba-533d-4b49-a40e-9604ee99efba.png">

- 회원의 정보는 spring security를 통해서 보안 처리됩니다.

<img width="1060" alt="스크린샷 2021-08-01 오후 8 28 58" src="https://user-images.githubusercontent.com/33304967/127769228-b6e335a9-e60a-4bb2-9c35-9252f2e050a1.png">
<img width="1060" alt="스크린샷 2021-08-01 오후 8 29 03" src="https://user-images.githubusercontent.com/33304967/127769231-29a72ddf-8242-4d33-bc15-a58624088ad5.png">

- EntityGraph를 이용해서 n+1 쿼리 문제를 해결했습니다.

<쓰기>

- 게시판에 글을 쓰기 위해서는 로그인이 필수입니다.

<img width="583" alt="스크린샷 2021-08-01 오후 8 08 30" src="https://user-images.githubusercontent.com/33304967/127768644-c69a6c86-88e1-42fe-b7d4-53deeaa48e57.png">
<img width="1433" alt="스크린샷 2021-08-01 오후 8 09 36" src="https://user-images.githubusercontent.com/33304967/127768646-cfd36153-7cd6-46d0-ac95-f1e4c4f58623.png">

- 로그인 후에는 글을 등록할 수 있습니다.

<img width="1433" alt="스크린샷 2021-08-01 오후 8 09 42" src="https://user-images.githubusercontent.com/33304967/127768650-9e9ead45-2724-44c5-9609-c2ec06e69aae.png">
   
    @NotNull
    @Size(min=2, max=30, message = "제목은 2자이상 30자 이하입니다")
    private String title;
    private String content;

- 글자 수를 조절하는 옵션을 추가하였습니다. 

<읽기>

- 게시판의 게시물을 읽기 위해서는 로그인이 필수입니다.


  <img width="355" alt="스크린샷 2021-08-01 오후 8 34 38" src="https://user-images.githubusercontent.com/33304967/127769384-742613b0-43ad-4a2a-b94d-574e99ca32ee.png">
  <img width="355" alt="스크린샷 2021-08-01 오후 8 35 07" src="https://user-images.githubusercontent.com/33304967/127769388-bf28670d-31de-4f2f-84ea-97c3dfa24bca.png">

- 하단의 인덱스는 게시글의 수에 따라 자동으로 증감합니다.

<삭제>

    @Secured("ROLE_ADMIN") //보안 취약 부분 고침
    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }


<img width="1439" alt="3" src="https://user-images.githubusercontent.com/33304967/127768431-541524e9-b10d-401b-9d55-5d5dc36b640d.png">
<img width="1439" alt="4" src="https://user-images.githubusercontent.com/33304967/127768441-650ce5ad-e774-4cd0-9595-88c82555eb67.png">

- User의 권한에 따라서 기능을 다르게 부여했습니다.
- User 외 Role 사이에 @ManyToMany 조인이 이뤄집니다.

<img width="1439" alt="5" src="https://user-images.githubusercontent.com/33304967/127768492-693d6a47-ad83-4c84-90fc-5d8af1219b50.png">
<img width="1439" alt="6" src="https://user-images.githubusercontent.com/33304967/127768494-eef07969-d214-4959-b3e9-4a5a57c12ef3.png">
<img width="1439" alt="7" src="https://user-images.githubusercontent.com/33304967/127768497-0c30fd54-a728-4f57-a6c2-88e5743e81d7.png">

- ROLE_ADMIN 권한을 가진 User에 의해서 삭제가 이루어집니다.



<검색>

    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
                    @RequestParam(required = false, defaultValue = "") String content) {
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return repository.findAll();
        }
        return repository.findByTitleOrContent(title, content);
    }

<img width="1433" alt="스크린샷 2021-08-01 오후 8 17 52" src="https://user-images.githubusercontent.com/33304967/127768893-84d2a3d4-5c84-4fbb-83d1-5662b72cd852.png">

- 제목이나 내용에 searchText를 가지고 있는 게시물을 리턴합니다.


