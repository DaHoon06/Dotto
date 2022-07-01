<template>
  <header id="header">
    <header-component
        @blurBackground="blurBackground"
        @notScroll="notScrollBody" />
    <status-component
        @blurBackground="blurBackground"
        @notScroll="notScrollBody"
    />
    <navigation-component
        :navigationType="navigationType"
    />
    <menu-button />
  </header>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import { MenuButton } from "@/components/common";
import HeaderComponent from "@/components/common/top/HeaderComponent.vue";
import NavigationComponent from "@/components/common/top/NavigationComponent.vue";
import StatusComponent from "@/components/common/top/StatusComponent.vue";

@Component({
  components: {
    HeaderComponent,
    MenuButton,
    NavigationComponent,
    StatusComponent
  }
})
export default class HeaderView extends Vue {
  @Prop({ default: 'home' }) navigationType?: string;

  //CSS 적용
  private blurBackground(isBlur: boolean) {
    this.$emit('blurBackground', isBlur);
  }
  private notScrollBody(scrollEvent: boolean) {
    this.$emit('notScrollBody', scrollEvent);
  }
}
</script>

<style >
#header {
  position: fixed;
  display: flex;
  justify-content: space-between;
  width: 100%;
  height: 144px;
  left: 0;
  top: 0;
  background: #FFFFFF;
  z-index: 10;
  flex-direction: column;
  border-bottom: 1px solid #E2E2E2;
}
</style>