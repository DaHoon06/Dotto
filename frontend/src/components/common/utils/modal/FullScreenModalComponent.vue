<template>
  <main
      id="modal"
      v-if="showModal"
      @backgroundFixed="backgroundFixed"
  >
    <header id="feed-modal-header">
      <img id="logo" src="@/assets/img/dotto.svg" alt="logo" />
    </header>

    <section id="full-modal-close-wrap">
      <button id="full-modal-close-btn" @click="modalClose">x</button>
    </section>
    <section class="modal-body">
      <feed-detail-component />
    </section>
  </main>
</template>

<script lang="ts">
import {Component, Emit, Prop, Vue} from "vue-property-decorator";
import FeedDetailComponent from "@/components/feed/FeedDetailComponent.vue";

@Component({
  components: {
    FeedDetailComponent
  }
})
export default class FullScreenModalComponent extends Vue {
  @Prop()
  showModal?: boolean;

  constructor() {
    super();
  }

  @Emit('backgroundFixed')
  private backgroundFixed(): boolean {
    return true
  }

  @Emit('modalClose')
  private modalClose(): boolean  {
    return false;
  }



}
</script>

<style scoped>
#logo {
  margin-left: 24px;
  margin-top: 12px;
  margin-bottom: 10px;
  width: 120px;
  height: 40px;
}

#feed-modal-header {
  position: fixed;
  top: 0;
  background: white;
  height: 63px;
  /*max-width: 1920px;*/
  width: 100%;
}

#modal {
  position: absolute;
  top: 50px;
  left: 0;
  width: 100%;
  height: 100%;
  background: #696969;
  z-index: 20;
}
#full-modal-close-wrap {
  float: right;
  margin-right: 40px;
  margin-top: 32px;
}
#full-modal-close-btn {
  width: 24px;
  height: 24px;
  color: white;
  border-radius: 30px;
}
.modal-body {
  padding: 0;
  position: absolute;
  top: 60%;
  left: 50%;
  width: 1200px;
  height: 800px;
  text-align: center;
  border-radius: 5px;
  box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);
  transform: translateX(-50%) translateY(-50%);
}


</style>