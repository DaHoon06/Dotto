<template>
  <main
      id="modal"
      v-if="showModal"
      @backgroundFixed="backgroundFixed"
  >
    <section>
      <button @click="modalClose">close</button>
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
#modal {
  position: absolute;
  top: 144px;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
  z-index: 20;
}

.modal-body {
  padding: 0;
  position: absolute;
  top: 40%;
  left: 50%;
  width: 1200px;
  height: 800px;
  text-align: center;
  border-radius: 5px;
  box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);
  transform: translateX(-50%) translateY(-50%);
}


</style>