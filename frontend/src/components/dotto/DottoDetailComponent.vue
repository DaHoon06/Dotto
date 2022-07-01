<template>
  <main id="dotto-detail-container">

    <section class="dotto-detail-wrapper">
      <article class="dotto-detail-section">
        <div>이 작업이 마음에 드시나요?</div>
       <button type="button" id="dotto-detail-like-btn">찜하기</button>
      </article>
      <article class="dotto-detail-flex-row">
        <div id="dotto-detail-img">아래 3장의 이미지 확대해서 보기 default 첫번째 이미지</div>
        <div class="dotto-detail-flex" id="dotto-detail-information">
          <h4>꽃과 레터링 할인 이벤트</h4>
          <div class="dotto-detail-flex-row">
            <div>조회수</div>
            <div>56회</div>
          </div>
          <div class="dotto-detail-flex-row">
            <div>50,000</div>
            <div>100,000</div>
          </div>
          <hr />
          <div class="dotto-detail-flex-row">
            <div>위치</div>
            <div>서울 마포구 (홍대)</div>
          </div>
          <div class="dotto-detail-flex-row">
            <div>장르</div>
            <div>블랙워크</div>
          </div>
          <div class="dotto-detail-flex-row">
            <div>소요시간</div>
            <div>2~3 시간</div>
          </div>
          <hr />
          <div>
            두달동안 할인가에 진행되고 있는 인기 최고 꽃과 레터링 입니다. 꽃은 손님의 탄생화로 수정해 드렸고 레터링은 기존 글씨체에 좋아 하시는 글귀를 넣어 작업했습니다 꽃, 글씨체 수정 등 요청사항다 반영해드립니다! 편하게 메세지 주세요!
          </div>
          <div>
            <span class="tag-area">태그들</span>
          </div>
          <hr />
          <article class="dotto-detail-section">
            <div class="dotto-detail-flex-row">
              <div>유저프로필</div>
              <div>
                <div>아이디</div>
                <div class="dotto-detail-flex-row">
                  <div>팔로워</div>
                  <div>158.8만명</div>
                </div>
              </div>
            </div>
            <button type="button" id="dotto-detail-follow-btn">팔로우</button>
          </article>
          <hr />
        </div>
      </article>

      <article class="dotto-detail-flex-row">
        <div class="dotto-detail-img-lists">이미지1</div>
        <div class="dotto-detail-img-lists">이미지2</div>
        <div class="dotto-detail-img-lists">이미지2</div>
      </article>
    </section>

    <section id="other-works-list">
      <h3 id="other-works-list-title">이 아티스트의 다른 작품 보기</h3>
      <div class="dotto-detail-flex-row">
        <button class="slide-btn" id="slide-left-btn"><</button>
        <div class="dotto-detail-other-design"></div>
        <div class="dotto-detail-other-design"></div>
        <div class="dotto-detail-other-design"></div>
        <div class="dotto-detail-other-design"></div>
        <button class="slide-btn" id="slide-right-btn">></button>
      </div>
    </section>

    <dotto-tabs-container id="dotto-tab" />

  </main>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import DottoTabsContainer from "@/components/dotto/tab/DottoTabsContainer.vue";


//TODO: 테스트 종료 후 class dotto-detail-comment-content height 수정

@Component({
  components: {
    DottoTabsContainer
  }
})
export default class DottoDetailComponent extends Vue {
  /*
    TODO:
      1. 게시글 상세 조회
      2. 해당 게시물 작성자 프로필 조회
      3 - 1. 게시글에 있는 댓글 조회
      3 - 2. 게시글에 있는 리뷰 조회
  * */


}
</script>

<style scoped>
hr {
  color: #E2E2E2;
  height: 2px;
  margin: 0;
}
#dotto-tab {
  margin-top: 160px;
}
/* 아웃라인 영역 */
#dotto-detail-container {
  display: flex;
  flex-direction: column;
  max-width: 1200px;
  min-height: 960px;
  height: 100%;
  width: 100%;
  margin: auto;
}

/*다른 아티스트 작품 보기*/
#other-works-list {
  margin-top: 175px;
}
#other-works-list-title {
  font-size: 24px;
  color: #222222;
  margin-bottom: 19px;
  font-weight: 600;
}

.dotto-detail-wrapper {
  margin-top: 10px;
}

/* 이미지 */
#dotto-detail-img {
  width: 50%;
  max-width: 640px;
  height: 640px;
  border: 1px solid gray;
}
.dotto-detail-img-lists {
  width: 80px;
  height: 80px;
  border: 1px solid gray;
  margin-right: 10px;
}
.dotto-detail-other-design {
  border: 1px solid gray;
  width: 288px;
  height: 288px;
  margin-right: 10px;
  margin-left: 10px;
}

#dotto-detail-information {
  width: 100%;
  max-width: 512px;
  margin-left: 48px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

/* 찜하기 버튼 */
#dotto-detail-like-btn {
  border-radius: 20px;
  width: 120px;
  height: 40px;
  color: white;
  background: #ff5831;
  font-weight: 600;
}
/* 팔로우 버튼 */
#dotto-detail-follow-btn {
  border-radius: 20px;
  width: 100px;
  height: 30px;
  color: #222222;
  background: #F5F5F5;
  font-size: 14px;
  font-weight: 600;
}
.slide-btn {
  text-align: center;
  position: absolute;
  top: 90%;
  border: 1px solid gray;
  border-radius: 30px;
  width: 48px;
  height: 48px;
  background: rgba(0,0,0, 0.5);
  color: white;
  font-size: 16px;
}
#slide-left-btn {
  left: 9%;
  top: 65rem;
  z-index: 10;
}
#slide-right-btn {
  right: 9%;
  top: 65rem;
  z-index: 10;
}

/* layout */
.dotto-detail-flex-row {
  display: flex;
  margin-top: 10px;
}

.dotto-detail-section {
  display: flex;
  justify-content: space-between;
}

.dotto-detail-other-design {
  display: flex;
}
</style>