<template>
  <article id="banner-container">
    <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="2500"
        style="text-shadow: 0 0 2px #FFFFFF"
        indicators
        controls
        label-next=""
        label-prev=""
        background="black"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd"
    >
      <b-carousel-slide v-for="(lists, index) of bannerLists" :key="index" :text='index+1+""'>
        <template #img>
          <img
              class="d-block img-fluid"
              :src="lists.img"
              alt="main-banner-1"
          >
        </template>
      </b-carousel-slide>

    </b-carousel>

  </article>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

export interface ImgProperty {
  img: string
}
@Component
export default class MainBannerComponent extends Vue {
  bannerLists: ImgProperty[];

  slide = 0;
  sliding = true;

  constructor() {
    super();
    this.bannerLists = [
      { img: require("@/assets/icons/main/sample/sample_banner.png") },
      { img: require("@/assets/icons/main/sample/sample_banner.png") },
      { img: require("@/assets/icons/main/sample/sample_banner.png") },
      { img: require("@/assets/icons/main/sample/sample_banner.png") },
    ]
  }

  created(): void {
    console.log('배너 이미지 불러오기');
  }

  private async init(): Promise<void> {
    const { data } = await this.axios.get('/api/banner');
    console.log(data);
  }

  private onSlideStart(): void {
    this.sliding = true
  }
  private onSlideEnd(): void {
    this.sliding = false;
  }
}
</script>

<style scoped>
#banner-container {
  margin-top: 100px;
  background: #ffffff;
  width: 100%;
}

#banner-container > img {
  width: 100%;
  height: 100%;
}
</style>