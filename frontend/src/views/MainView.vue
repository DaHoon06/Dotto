<template>
  <div id="main-home-container">
    <section>
      <main-banner-component />
    </section>

    <main id="main-wrapper" >

      <section id="home-dotto-wrapper" >
        <div class="main-component-wrapper">
          <div class="main-items-wrapper">
            <h5>닷투 게시판</h5>
            <small>타투이스트가 올린 다양한 작품 중 내 취향을 찾아보세요!</small>
          </div>
          <div>
            <router-link
                to="/dotto/board/index"
                class="show-all-lists"
            >전체보기
              <img src="@/assets/icons/main/redirect-arrow.png" class="redirect-arrow" alt="dotto-board" />
            </router-link>
          </div>
        </div>
        <dotto-component
            :limit="limit"
        />
      </section>

      <section id="home-seekers-wrapper" >
        <div class="main-component-wrapper">
          <div class="main-items-wrapper">
            <h5>닷찾사 게시판</h5>
            <small>내가 원하는 도안을 제시하고 타투이스트에게 답변 받아보세요!</small>
          </div>
          <div>
            <router-link
                to=""
                class="show-all-lists"
            >전체보기
              <img src="@/assets/icons/main/redirect-arrow.png" class="redirect-arrow" alt="dotto-board" />
            </router-link>
          </div>
        </div>
        닷찾사 게시판 컴포넌트 위치 ( limit: 8개 )
      </section>

      <section id="home-feed-wrapper" >
        <div class="main-component-wrapper">
          <div class="main-items-wrapper">
            <h5>FEED</h5>
            <small>자유롭게 이야기를 나눠요!</small>
          </div>
          <div>
            <router-link
                to=""
                class="show-all-lists"
            >전체보기
              <img src="@/assets/icons/main/redirect-arrow.png" class="redirect-arrow" alt="dotto-board" />
            </router-link>
          </div>
        </div>
        FEED 게시판 컴포넌트 위치 ( limit: 8개 )
      </section>

    </main>

    <aside id="side-button-container">
      <top-scroll-button />
    </aside>

  </div>
</template>

<script lang="ts">
import { Component, Emit, Vue } from "vue-property-decorator";
import {
  FooterComponent,
  MenuButton,
  TopScrollButton,
} from "@/components/common";
import FollowListComponent from "@/components/main/FollowListComponent.vue";
import MainBannerComponent from "@/components/main/MainBannerComponent.vue";
import { DottoComponent } from "@/components/dotto";

@Component({
  components: {
    MainBannerComponent,
    FollowListComponent,
    TopScrollButton,
    FooterComponent,
    MenuButton,
    DottoComponent
  }
})
export default class MainView extends Vue {
  limit = 8;
  //TODO: 3가지 항목 변수 및 인터페이스 정의


  created() {
    this.changeNavType();
    this.init();
  }

  private init() {
    /*TODO:
       1. 닷투 게시판 리스트 불러오기 limit 8
       2. 닷찻사 게시판 리스트 불러오기 limit 8
       3. FEED 리스트 불러오기 limit 8
    */
    this.setData();
  }

  private async setData(): Promise<void> {
    await this.getDottoBoardData();
    await this.getDottoSeekersData();
    await this.getFeedData();
  }

  private async getDottoBoardData(): Promise<void> {
    console.log('닷투');
  }

  private async getDottoSeekersData(): Promise<void> {
    console.log('닷찾사');
  }

  private async getFeedData(): Promise<void> {
    console.log('피드');
  }

  @Emit('changeNavType')
  private changeNavType(): string {
    return 'home';
  }
}
</script>

<style scoped>
small {
  color: #696969;
  margin-left: 10px;
}

#main-home-container {
  width: 100%;
  height: 100%;
  min-height: 800px;
  background: white;
  margin-top: 144px;
}

#main-wrapper {
  max-width: 1200px;
  width: 100%;
  margin: 0 auto 10em auto;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}
#home-dotto-wrapper {
  max-width: 1200px;
  width: 100%;
}

.main-component-wrapper {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.main-items-wrapper {
  display: flex;
  align-items: center;
}

.show-all-lists {
  color: #222222;
  font-weight: bold;
}
.show-all-lists:hover {
  color: #696969;
}

.redirect-arrow {
  width: 20px;
  height: 5px;
}

/* side button */
#side-button-container {
  position: fixed;
  top: 50%;
  right: 48px;
}

@media screen and (max-width: 1260px){
  #navigation-container {
    display: none;
  }
}

@media screen and (max-width: 500px){



}

</style>