<template>
  <div id="my-feed-container" :class="fixed ? backgroundFixedCss : ''">

    <div id="my-feed-list-container">
      <div class="my-feed-user-info-wrapper my-feed-items">
        <span>
          <img src="@/assets/icons/default.svg" class="my-feed-profile" alt="profile" />
        </span>
        <div class="my-feed-user-info my-feed-items">
          <span class="my-feed-nickName">닉네임</span>
          <span class="my-feed-posting-time">15분전</span>
        </div>
      </div>
      <div>
        <div>
          <img class="my-feed-img" src="@/assets/icons/main/sample/sample_img07.png" alt="sample07" />
        </div>
        <div style="text-align: center">여러장일 경우 슬라이드</div>
      </div>
      <div class="my-feed-items">
        <span>
          <img class="my-feed-icons" src="@/assets/icons/myfeed/like-icons.svg" alt="like-icons" />
        </span>
        <span>
          <img class="my-feed-icons my-feed-message" src="@/assets/icons/myfeed/message.svg" alt="message" />
        </span>
      </div>
      <div class="like-count-wrapper my-feed-items">
        <span class="like-count-label">좋아요</span>
        <span class="like-count">1640</span>
      </div>
      <div class="my-feed-items">
        <span class="my-feed-nickName">닉네임</span>
        <span class="my-feed-title">게시글 제목부분</span>
      </div>
      <hr />
      <div class="my-feed-comment-wrapper">
        <span>
          <img src="@/assets/icons/default.svg" class="my-feed-my" alt="my" />
        </span>
        <input type="text" class="my-feed-comment" placeholder="댓글을 입력하세요..." />
        <button class="my-feed-comment-postingBtn">게시</button>
      </div>
    </div>
    <button @click="test">TEST</button>

    <transition name="fade">
      <FullScreenModalComponent
          :showModal="showModal"
          @modalClose="modalClose"
          @backgroundFixed="backgroundFixed"
      />
    </transition>

  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import FullScreenModalComponent from "@/components/common/utils/modal/FullScreenModalComponent.vue";
@Component({
  components: {FullScreenModalComponent}
})
export default class FeedComponent extends Vue {
  backgroundFixedCss = 'backgroundFixed';
  fixed = false;
  showModal = false;

  test() {
    this.showModal = true;
  }

  modalClose(type: boolean) {
    this.showModal = type;
  }

  private backgroundFixed(type: boolean) {
    this.fixed = type;
  }

}
</script>

<style scoped>
.backgroundFixed {
  position: fixed;
}
/* MyFeedComponent 랑 중복 -> 아이콘 사용 안됨 */
#my-feed-container {
  width: 60%;
  margin: 144px auto 0 auto;
  height: 100%;
  background: #f6f6f6;
  min-height: 1200px;
}

#my-feed-list-container {
  background: white;
  border-radius: 10px;
  width: 28vw;
  min-height: 400px;
  margin: 3em auto auto auto;
}

.my-feed-profile, .my-feed-my {
  width: 30px;
}

/* 마이피드 템플릿 카드 영역 */
.my-feed-img {
  width: 100%;
}

.my-feed-comment-postingBtn {
  color: #2E82FF;
  font-size: 12px;
}
.my-feed-comment {
  border: none;
  width: 75%;
  margin-left: 20px;
  font-size: 0.9rem;
  outline: none;
}
.my-feed-comment-wrapper {
  padding-bottom: 10px;
  margin-left: 10px;
}
/* 상단 닉네임, 시간, 프로필 영역 */
.my-feed-user-info-wrapper {
  padding: 10px 0 5px 0;
  display: flex;
  align-items: center;
}
.my-feed-user-info {
  display: flex;
  flex-direction: column;
}
.my-feed-posting-time {
  font-size: 10px;
}

.my-feed-items {
  margin-left: 10px;
}
/* 좋아요, 개수 */
.like-count-label {
  font-size: 14px;
  font-weight: 700;
}
.like-count {
  font-size: 12px;
  margin-left: 10px;
}
.like-count::after {
  content: '개'
}
/* 하단 닉네임, 제목 */
.my-feed-title {
  margin-left: 10px;
  font-size: 13px;
}
.my-feed-nickName {
  font-weight: bold;
  font-size: 14px;
}
/* 마이피드 아이콘 */
.my-feed-icons {
  width: 15px;
  color: #9E9E9E;
}
.my-feed-message {
  margin-left: 10px;
}
/*----------------------*/
</style>