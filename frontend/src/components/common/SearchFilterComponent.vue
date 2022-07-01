<template>

  <aside id="filter-container">
    <perfect-scrollbar>
      <div id="side-menu-flex">
        <section id="filter-tag-wrapper">
          <div id="filter-tag-items">
            <article id="tag-area">
              <h4 class="filter-title">#태그</h4>
              <button @click="showTags">
                <img class="filter-drop-btn" src="@/assets/icons/nav/filter-btn.svg" alt="filter" />
              </button>
            </article>

            <transition name="fade">
              <article id="tag-list" v-if="showTagArea">
                <span class="filter-tag">블랙앤그레이</span>
                <span class="filter-tag">레터링</span>
                <span class="filter-tag">트라이얼</span>
                <span class="filter-tag">레터링</span>
                <span class="filter-tag">트라이얼</span>
                <span class="filter-tag">올드스쿨</span>
                <span class="filter-tag">뉴스쿨</span>
              </article>
            </transition>

          </div>
        </section>

        <hr />

        <section id="filter-location-wrapper">
          <div id="filter-location-items">
            <article id="location-area">
              <h4 class="filter-title">지역</h4>
              <button class="filter-drop-btn" @click="showLocation">
                <img class="filter-drop-btn" src="@/assets/icons/nav/filter-btn.svg" alt="filter" />
              </button>
            </article>

            <transition name="fade">
              <article v-if="showLocationArea" id="filter-search-items">
                <input
                    type="text"
                    ref="refKeyword"
                    v-model="keyword"
                    id="filter-search-bar" />
                <button class="filter-drop-btn filter-search-btn" id="search-btn">
                  <img id="search-icon"  src="@/assets/icons/nav/search.svg" alt="search" />
                </button>
              </article>
            </transition>
          </div>
        </section>

        <section>
          <article>
            <button
                class="filter-menu-button"
                id="reset"
                @click="reset"
                type="button"
            >초기화</button>
            <button
                class="filter-menu-button"
                id="show-result"
                type="button"
            >결과보기</button>
          </article>
        </section>
      </div>
    </perfect-scrollbar>
  </aside>
</template>

<script lang="ts">
import { Component, Ref, Vue } from "vue-property-decorator";

@Component
export default class SearchFilterComponent extends Vue {

  showSortArea= false;
  showTagArea = false;
  showLocationArea = false;
  keyword = '';
  @Ref() readonly refKeyword!: HTMLElement;

  constructor() {
    super();
  }

  private reset() {
    this.keyword = '';
    this.$nextTick(() => this.refKeyword.focus());
  }


  private showTags() {
    this.showTagArea = !this.showTagArea;
  }

  private showLocation() {
    if (this.showLocationArea) this.$nextTick(() => this.refKeyword.focus());
    if (this.showLocationArea) this.keyword = '';
    this.showLocationArea = !this.showLocationArea;
  }


}
</script>

<style scoped>
hr {
  border: 0;
  background: #a2a2a2;
  width: 296px;
  height: 2px;
}
#filter-container {
  animation: fadein 3s;
  height: 936px;
  width: 296px;
  margin-top: 10em;
  display: inline-block;
}

#side-menu-flex {
  display: flex;
  flex-direction: column;
  height: 100%;
  align-items: stretch;
}

#filter-tag-wrapper {
  height: 386px;
}
#filter-location-wrapper {
  height: 548px;
}

.filter-title {
  font-weight: 500;
  font-size: 16px;
  color: #222222;
}

#tag-area, #location-area {
  display: flex;
  justify-content: space-between;
}

#filter-search-bar {
  border: none;
  outline: none;
  padding: 4px;
  border-bottom: 2px solid #b7b7b7;
  width: 208px;
  height: 36px;
  color: #222222;
}

/* 필터 안쪽 영역 잡기 위함 */
#filter-tag-items {
  padding: 72px 40px;
}
#filter-location-items {
  padding: 24px 40px;
}
#filter-search-items {
  margin-top: 24px;
}

/*----- 버튼들 -----*/
#search-btn {
  position: relative;
  right: 20px;
  bottom: 8px;
  width: 18px;
  height: 18px;
}

.filter-menu-button {
  box-sizing: border-box;
  border: 1px solid #E2E2E2;
  font-weight: 400;
  border-radius: 4px;
  line-height: 17px;
  height: 3em;
  font-size: 14px;
}
.filter-drop-btn {
  width: 12px;
}

#reset {
  width: 64px;
  height: 40px;
  color: #919191;
  margin-left: 32px;
  margin-right: 12px;
}

#show-result {
  width: 164px;
  height: 40px;
  background: #222222;
  color: #ffffff;
}

.filter-search-btn {
  width: 20px;
  height: 20px;
}

/* 태그 */
.filter-tag {
  border: 1px solid #F5F5F5;
  background: #F5F5F5;
  color: #222222;
  padding: 8px;
  margin-right: 8px;
  font-size: 14px;
  margin-top: 16px;
}

#tag-list {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  justify-content: flex-start;
}

/* perfect-scroll */
.ps {
  height: 937px;
  width: 310px;
}

/*------ 반응형 -----*/
@media screen and (max-width: 1440px){

}

@media screen and (max-width: 1260px) {

}

</style>