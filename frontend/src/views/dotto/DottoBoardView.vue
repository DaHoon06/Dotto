<template>
  <div id="dotto-board-container"
       :class="dottoBoardBackground === 'main' ? '' : otherBgColor">
    <div id="side-search-filter-wrapper">
      <transition name="fade">
        <search-filter-component
            v-show="showSearchFilter" />
      </transition>
    </div>
    <router-view
        @showFilter="showFilter"
        @changeBackground="changeBackground"/>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Vue } from "vue-property-decorator";
import { DottoComponent } from "@/components/dotto";
import { SearchFilterComponent } from "@/components/common";

@Component({
  components: {
    DottoComponent,
    SearchFilterComponent
  }
})
export default class DottoBoardView extends Vue {
  dottoBoardBackground = 'main';
  showSearchFilter = false;
  otherBgColor = 'dotto-background-other';

  created(): void {
    this.changeNavType();
  }

  @Emit('changeNavType')
  private changeNavType(): string {
    return 'community';
  }

  private showFilter(show: boolean): void {
    this.showSearchFilter = show;
  }

  private changeBackground(type: string) {
    this.dottoBoardBackground = type;
  }
}
</script>

<style scoped>
#dotto-board-container {
  width: 100%;
  height: 100%;
  min-height: 800px;
  background: white;
  margin-top: 170px;
}

#side-search-filter-wrapper {
  position: absolute;
  top: 0;
}

.dotto-background-other {
  background: #f6f6f6!important;
}
</style>