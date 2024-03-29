package com.spring.mvc.chap05.common;

import com.mysql.cj.jdbc.BlobFromLocator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @ToString @EqualsAndHashCode
public class PageMaker {

    // 페이지 버튼
    private int begin, end, finalPage; // 페이지 시작번호, 끝번호 (1~10, 11~20) 보정된 끝 페이지 번호
    
    // 이전, 다음 번호 활성화 여부
    private boolean prev, next;

    // 현재 사용자가 요청한 페이지 정보.
    private Page page;

    // 총 게시물 수
    private int totalCount;

    public PageMaker(Page page, int totalCount) {
        this.page = page;
        this.totalCount = totalCount;

        makePageInfo();
    }

    // 한 화면에 보여질 페이지 버튼 개수
    private static final int PAGE_COUNT = 10;
    private void makePageInfo() {

        /*
        공식: Math.ceil(현재 위치한 페이지 번호 / 한 화면당 보여질 버튼개수)
		* 한 화면당 보여질 버튼개수

# 4. 시작 페이지 번호 계산하기
공식: (끝 페이지 번호 - 한 화면에 보여줄 페이지 버튼 수) + 1

# 5. 끝 페이지 보정

- 총 게시물 수가 324개이고, 한 페이지당 10개의 게시물을 보여준다.
- 그리고 이 사람은 현재 31페이지를 보고 있다.
- 그리고 한 화면에 페이지 버튼은 10개가 배치된다.
- 그렇다면, 위 공식에 의한 끝 페이지는 몇 번으로 계산되는가? -> 40번
- 하지만 실제 끝 페이지는 몇 번에서 끝나면 되는가? -> 33번

# 5-1. 이전 버튼 활성화 여부 설정
- 시작 페이지 번호가 1로 구해진 시점에서는 비활성 처리, 나머지는 활성.

# 5-2. 다음 버튼 활성화 여부 설정
- 공식: 보정 전 끝 페이지 번호 x 한 페이지에 들어갈 게시물 수 >= 총 게시물 수
	-> 비활성.

# 5-3. 끝 페이지 값 보정
- 다음 버튼이 비활성화 되었다면 총 게시물 수에 맞춰 끝 페이지 번호를
 재 보정합니다.
공식: Math.ceil(총 게시물의 개수 / 한 페이지에 보여줄 게시물 수)

         */
        // 끝 페이지 번호(end) 계산
        this.end = (int) (Math.ceil((double) page.getPageNo() / PAGE_COUNT) * PAGE_COUNT);

        // 시작 페이지 번호(begin) 계산
        this.begin = (end - PAGE_COUNT) + 1;

        // 이전 버튼 활성화 여부(prev)
//        if (begin == 1) {
//            this.prev = false;
//        } else {
//            this.prev = true;
//        }
        this.prev = begin > 1;

        this.finalPage = (int) Math.ceil((double) totalCount / page.getAmount());
     
        // 마지막 페이지 구간에서 end 값을 finalPage 값으로 변경
        if (this.finalPage < this.end) {
            this.end = this.finalPage;
        }
            
        // 다음 버튼 활성화 여부 (next)
        this.next = this.end < this.finalPage;

        /*
        // 다음 버튼 활성화 여부(next)
        if(end * page.getAmount() >= totalCount) {
            this.next = false;
        } else {
            this.next = true;
        }
        if(!next) {
            this.end = (int) Math.ceil((double) totalCount / page.getAmount());

         */
    }
}

















