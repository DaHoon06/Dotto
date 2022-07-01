<template>
  <div>
    <router-view />
  </div>
</template>

<script lang="ts">
import { Component, Emit, Vue } from "vue-property-decorator";

@Component
export default class FeedView extends Vue {

  created(): void {
    this.changeNavType();
  }

  @Emit('changeNavType')
  private changeNavType(): string {
    return 'feed';
  }

}
</script>

<style scoped>

</style>