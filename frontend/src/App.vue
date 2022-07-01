<template>
  <div id="app" :class='`${scrollPrevent}`'>
    <header-view
        @blurBackground="blurBackground"
        @notScrollBody="notScrollBody"
        :navigationType="navigationTypeComputed"
    />
    <router-view
        :class='`${blurCss}`'
        @changeNavType="changeNavType" />
    <footer-component :class='`${blurCss}`' />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import HeaderView from "@/views/HeaderView.vue";
import { FooterComponent } from "@/components/common";
import { BLUR, SCROLL } from "@/interfaces/common/ICommon";


@Component({
  components: {
    FooterComponent,
    HeaderView
  }
})
export default class App extends Vue {
  blurCss: BLUR = BLUR.OFF;
  scrollPrevent: SCROLL = SCROLL.ON;
  navigationType = '';

  private blurBackground(isBlur: boolean) {
    isBlur ? this.blurCss = BLUR.ON : this.blurCss = BLUR.OFF;
    isBlur ? this.scrollPrevent = SCROLL.OFF : this.scrollPrevent = SCROLL.ON;
  }

  private notScrollBody(scrollEvent: boolean) {
    scrollEvent ? this.scrollPrevent = SCROLL.OFF : this.scrollPrevent = SCROLL.ON;
  }
  private changeNavType(type: string): void {
    this.navigationTypeComputed = type;
  }

  private set navigationTypeComputed(type: string) {
    this.navigationType = type;
  }
  private get navigationTypeComputed() {
    return this.navigationType;
  }

}
</script>

<style>

/* 아래 항목 : 이벤트에 따른 CSS 적용 */
.setBlur {
  filter: blur(4px);
  width: 100%;
}
.notScroll {
  position: fixed;
  width: 100%;
  -ms-overflow-style: none;
  scrollbar-width: none;
}
.notScroll::-webkit-scrollbar {
  display: none;
  width: 0 !important;
}
</style>
