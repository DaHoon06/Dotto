<template>
  <div id="dotto-comment-container">

    <section id="dotto-add-comment-wrapper">
      <div id="comment-profile-img-div">
        <img src="@/assets/icons/default.svg" class="profile-img" alt="my" />
      </div>
      <div>
        <input
            type="text"
            placeholder="댓글을 남겨주세요."
            id="dotto-add-comment-input"
        />
      </div>
      <div id="comment-btn-wrapper">
        <button
            type="button"
            class="dotto-comment-btn"
            id="cancel">취소</button>
        <button
            type="button"
            :disabled="activeBtn"
            class="dotto-comment-btn"
            id="posting">작성</button>
      </div>
    </section>

    <section id="dotto-detail-comment-container">
      <!--TODO 반복문 시작 태그 -->
      <article class="dotto-comment-wrapper">
        <div class="dotto-tabs-flex-row">
          <div class="dotto-tab-profile">
            <img src="@/assets/icons/default.svg" class="profile-img" alt="my" />
            <div class="dotto-comment-items">
              <div class="dotto-detail-comment-user">
                <div>전다훈</div>
                <div>2022.05.19</div>
              </div>
              <div class="dotto-comment-contents">
                깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!요~!~!~!~!
              </div>
            </div>
          </div>
          <div class="tabs-side-menu-btn-wrapper">
            <button type="button" @click="showCommentOptions">
              <img src="@/assets/icons/board/dotto/tabs-side-menu-btn.png" class="tabs-side-menu-btn" alt="options"/>
            </button>
            <div class="dotto-detail-comment-options" v-if="showMenuList">
              <div class="tabs-side-menu-btn-div">
                <button class="">수정</button>
              </div>
              <div class="tabs-side-menu-btn-div">
                <button>삭제</button>
              </div>
              <div class="tabs-side-menu-btn-div">
                <button>신고</button>
              </div>
            </div>
          </div>
        </div>
      </article>

      <article class="dotto-comment-wrapper">
        <div class="dotto-tabs-flex-row">
          <div class="dotto-tab-profile">
            <img src="@/assets/icons/default.svg" class="profile-img" alt="my" />
            <div class="dotto-comment-items">
              <div class="dotto-detail-comment-user">
                <div>전다훈</div>
                <div>2022.05.19</div>
              </div>
              <div class="dotto-comment-contents">
                깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!요~!~!~!~!
              </div>
            </div>
          </div>
          <div class="tabs-side-menu-btn-wrapper">
            <button type="button" @click="showCommentOptions">
              <img src="@/assets/icons/board/dotto/tabs-side-menu-btn.png" class="tabs-side-menu-btn" alt="options"/>
            </button>
            <div class="dotto-detail-comment-options" v-if="showMenuList">
              <div class="tabs-side-menu-btn-div">
                <button class="">수정</button>
              </div>
              <div class="tabs-side-menu-btn-div">
                <button>삭제</button>
              </div>
              <div class="tabs-side-menu-btn-div">
                <button>신고</button>
              </div>
            </div>
          </div>
        </div>
      </article>

      <article class="dotto-comment-wrapper">
        <div class="dotto-tabs-flex-row">
          <div class="dotto-tab-profile">
            <img src="@/assets/icons/default.svg" class="profile-img" alt="my" />
            <div class="dotto-comment-items">
              <div class="dotto-detail-comment-user">
                <div>전다훈</div>
                <div>2022.05.19</div>
              </div>
              <div class="dotto-comment-contents">
                깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!깔끔하고 예쁘네요~!~!~!~!요~!~!~!~!
              </div>
            </div>
          </div>
          <div class="tabs-side-menu-btn-wrapper">
            <button type="button" @click="showCommentOptions">
              <img src="@/assets/icons/board/dotto/tabs-side-menu-btn.png" class="tabs-side-menu-btn" alt="options"/>
            </button>
            <div class="dotto-detail-comment-options" v-if="showMenuList">
              <div class="tabs-side-menu-btn-div">
                <button class="">수정</button>
              </div>
              <div class="tabs-side-menu-btn-div">
                <button>삭제</button>
              </div>
              <div class="tabs-side-menu-btn-div">
                <button>신고</button>
              </div>
            </div>
          </div>
        </div>
      </article>

    </section>

  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class DottoCommentComponent extends Vue {
  showMenuList = false;
  activeBtn = true;

  private showCommentOptions() {
    this.showMenuList = !this.showMenuList;
  }
}
</script>

<style scoped>
#dotto-comment-container {
  margin-top: 84px;
}
#dotto-add-comment-wrapper {
  display: flex;
  align-items: baseline;
  width: 100%;
  max-width: 1200px;
}
#dotto-add-comment-input {
  width: 896px;
  height: 56px;
  outline: none;
  border: none;
  border-bottom: 1px solid #E2E2E2;
  margin-left: 32px;
}
#comment-profile-img-div {
  text-align: center;
  width: 64px;
  height: 64px;
}

/* 버튼 관련 */
#comment-btn-wrapper {
  text-align: center;
  margin-left: 20px;
  width: 100%;
}
#cancel {
  font-size: 18px;
  color: #919191;
}
#posting {
  color: #BDBDBD;
  font-size: 18px;
  background: #F5F5F5;
  margin-left: 12px;
}
.dotto-comment-btn {
  width: 72px;
  height: 46px;
}

/* 공통 */
#dotto-detail-comment-container {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-top: 80px;
  margin-bottom: 80px;
  width: 100%;
  max-width: 1200px;
  height: 100%;
  min-height: 112px;
}
.dotto-tabs-flex-row {
  display: flex;
  justify-content: space-between;
}
.dotto-tab-profile {
  display: flex;
  width: 100%;
}
.dotto-comment-wrapper {
  max-width: 1200px;
  width: 100%;
  margin-top: 20px;
}
.dotto-comment-contents {
  overflow-y: auto;
  width: 100%;
}
.dotto-comment-items {
  margin-left: 32px;
  width: 100%;
}
.dotto-detail-comment-user {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
}
.dotto-detail-comment-user > div:nth-child(1){
  font-size: 18px;
  color: #222222;
  font-weight: 600;
}
.dotto-detail-comment-user > div:nth-child(2) {
  font-size: 18px;
  color: #919191;
  margin-left: 8px;
}

.tabs-side-menu-btn-wrapper {
  margin-left: 152px;
}
.tabs-side-menu-btn {
  width: 32px;
  height: 32px;
}
.tabs-side-menu-btn-div {
  width: 100%;
  height: 32px;
}
.tabs-side-menu-btn-div {
  padding: 7px 0 8px 16px;
}
.profile-img {
  width: 64px;
  height: 64px;
}

.dotto-detail-comment-options {
  font-size: 14px;
  color: #222222;
  border: 1px solid #E2E2E2;
  border-radius: 8px;
  box-shadow: 0 1px 5px 1px #e2e2e2;
  width: 144px;
  height: 116px;
  background: white;
  z-index: 12;
  position: absolute;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
/*------------------*/


</style>