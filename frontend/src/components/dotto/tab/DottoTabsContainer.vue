<template>
  <div>

    <section class="dotto-detail-wrapper dotto-detail-flex-row">
      <div
          class="dotto-detail-tabs-wrapper"
          :class="activeTabs === 'Review' ? 'activeTabs' : ''"
      >
        <button
            type="button"
            class="tabs-title"
            @click="showDottoReview">리뷰</button>
        <span class="tabs-count">1,200</span>
      </div>
      <div
          class="dotto-detail-tabs-wrapper"
          :class="activeTabs === 'Comment' ? 'activeTabs' : ''">
        <button
            type="button"
            class="tabs-title"
            @click="showDottoComment">댓글</button>
        <span class="tabs-count">1,200</span>
      </div>

    </section>

    <hr />

    <component
        :is="dynamicComponent"/>

    <button id="dotto-detail-more-review">리뷰 더보기</button>

  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import DottoCommentComponent from "@/components/dotto/tab/DottoCommentComponent.vue";
import DottoReviewComponent from "@/components/dotto/tab/DottoReviewComponent.vue";

@Component({
  components: {
    DottoCommentComponent,
    DottoReviewComponent
  }
})
export default class DottoTabsContainer extends Vue {
  dottoTabType = 'Review';
  activeTabs = 'Review';

  private showDottoReview() {
    this.dottoTabType = 'Review';
    this.activeTabs = 'Review';
  }

  private showDottoComment() {
    this.dottoTabType = 'Comment';
    this.activeTabs = 'Comment';
  }

  private get dynamicComponent() {
    switch (this.dottoTabType) {
      case 'Review':
        return DottoReviewComponent;
      case 'Comment':
        return DottoCommentComponent;
    }
  }

}
</script>

<style scoped>
hr {
  margin: 0;
}
.dotto-detail-tabs-wrapper {
  display: flex;
  margin-top: 10px;
  margin-right: 10px;
  align-items: center;
  width: 200px;
  height: 80px;
}

.dotto-detail-wrapper {
  margin-top: 10px;
}

.dotto-detail-flex-row {
  display: flex;
  margin-top: 10px;
}

#dotto-detail-more-review {
  max-width: 1200px;
  width: 100%;
  height: 64px;
  text-align: center;
  border: 1px solid #E2E2E2;
  border-radius: 2px;
  margin-bottom: 100px;
}
.tabs-title {
  font-size: 24px;
  color: #BDBDBD;
  margin-left: 51px
}
.tabs-count {
  font-size: 18px;
  color: #BDBDBD;
  margin-left: 8px;
}
/* 선택된 탭 */
.activeTabs {
  font-weight: bold;
  color: #222222;
  border-bottom: 4px solid black;
}
.activeTabs > .tabs-title {
  color: #222222;
  font-weight: 600;
}
.activeTabs > .tabs-count {
  color: #222222;
  font-weight: lighter;
}
</style>