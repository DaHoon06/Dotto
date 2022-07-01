<template>
  <div id="feed-detail-container">
    <section id="feed-detail-img-list">

    </section>
    <section id="feed-detail-contents">
      with react
      <article>
        <div>
          <div>profile</div>
          <div>
            <div>User_name</div>
            <div>
              <div>타투이스트</div>
              <div>1시간전</div>
            </div>
          </div>
        </div>
        <div>
          <button>메뉴버튼</button>
        </div>
      </article>
      <div>
        콘텐츠 영역
      </div>

      <hr />

      <!--#TODO 댓글: 반복 영역 -->
      <perfect-scrollbar>
        <article class="feed-detail-comment">
          <div>
            <div>
              <div>profile</div>
              <div>
                <div>User_name</div>
                <div>1시간전</div>
              </div>
            </div>
            <div>
              콘텐츠 영역
            </div>
          </div>
        </article>
      </perfect-scrollbar>

      <article >
        <div>
          <span>하트 개수</span>
          <span>댓글 개수</span>
        </div>
      </article>

      <hr />

      <article>
        <div>
          프로필 이미지
        </div>
        <div>
          <input type="text" placeholder="댓글을 입력하세요..." />
        </div>
      </article>

    </section>




  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class FeedDetailComponent extends Vue {

}
</script>

<style scoped>
#feed-detail-container {
  display: flex;
  width: 100%;
  height: 100%;
}

#feed-detail-img-list {
  background: #222222;
  width: 800px;
  height: 800px;
  border-radius: 5px 0 0 5px ;
}

#feed-detail-contents {
  background: #ffffff;
  width: 400px;
  height: 800px;
  border-radius: 0 5px 5px 0;
}

/* 댓글 영역 */
.feed-detail-comment {
  width: 400px;
  display: flex;
}
</style>