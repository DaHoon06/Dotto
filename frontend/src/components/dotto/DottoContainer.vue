<template>
  <div id="main-container" :class="showFilterComponent ? 'showSearchFilter' : ''" >
    <follow-list-component />

    <section id="filter-area">
      <span class="filter-section">
        <button @click="showSort" >
          <span class="filter-text">{{ sendSortType }}</span>
          <img class="side-menu-drop-btn filter-text" src="@/assets/icons/nav/filter-btn.svg" alt="sort" />
        </button>
        <sort-component
            @typeName="typeName"
            :selectedType="filterType"
            :showSortComponent="showSortComponent" />
      </span>

      <span id="filter" class="filter-section">
        <button @click="showFilter">
          <span class="filter-text">FILTER</span>
          <img class="filter-text filter-toggle-img" src="@/assets/icons/main/filter.svg" alt="filter" />
        </button>
      </span>
    </section>

    <dotto-component
        :limit="limit"
    />

    <aside id="side-button-container">
      <dotto-posting-button />
      <top-scroll-button />
    </aside>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Vue } from "vue-property-decorator";
import { TopScrollButton, SortComponent } from "@/components/common";
import FollowListComponent from "@/components/main/FollowListComponent.vue";
import { DottoComponent, DottoPostingButton } from "@/components/dotto/index";

@Component({
  components: {
    DottoPostingButton,
    TopScrollButton,
    SortComponent,
    FollowListComponent,
    DottoComponent
  }
})
export default class DottoComponentContainer extends Vue {
  showSortComponent = false;
  showFilterComponent = false;
  filterType: string;
  showSearchFilter = 'showSearchFilter';
  limit = 16;
  constructor() {
    super();
    this.filterType = '최신순'
  }

  created(): void {
    this.changeBackground();
  }

  private showSort() {
    this.showSortComponent = !this.showSortComponent;
  }

  private typeName(type: string) {
    this.filterType = type;
  }

  private get sendSortType() {
    return this.filterType;
  }

  @Emit('showFilter')
  private showFilter() {
    this.showFilterComponent = !this.showFilterComponent;
    return this.showFilterComponent;
  }

  @Emit('changeBackground')
  private changeBackground() {
    return 'main';
  }

}
</script>

<style scoped>
#main-container {
  max-width: 1200px;
  width: 100%;
  margin: 100px auto 10em auto;
  min-height: 1200px;
  height: 100%;
  display: flex;
  flex-direction: column;
}

#filter-area {
  height: 30px;
  display: inline-block;
  text-align: right;
  padding-right: 5em;
}

#tattoo-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
}

.side-menu-drop-btn {
  width: 12px;
}

.tattoo-board-list {
  box-shadow: 0 1px 2px 1px #adadad;
  border-radius: 2px;
  width: 260px;
  height: 380px;
  margin-top: 1em;
  margin-left: 1em;
}

.filter-text {
  font-size: 14px;
  margin-left: 5px;
  font-weight: 600;
}

.filter-toggle-img {
  width: 18px;
}

.filter-section {
  margin-left: 20px;
}

.tattoo-img {
  width: 240px;
  margin-left: 10px;
  margin-top: 8px;
}

.tag-area {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  padding: 5px 8px;
  background: #F5F5F5;


  /* 서울 홍대 */
  position: static;
  width: 40px;
  height: 23px;
  left: 8px;
  top: 4px;

  font-family: 'Pretendard';
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 14px;
  color: #696969;

  /* Inside auto layout */
  flex: none;
  order: 0;
  flex-grow: 0;
  margin: 0px 0px;
}

.tattoo-board-list-info {
  font-size: 10px;
  color: #919191;
  margin-left: 0.6rem;
  margin-top: 0.225rem;
}

.title {
  color: #222222;
  font-size: 14px;
  font-weight: 700;
}

.user-name {
  font-weight: 400;
  line-height: 14px;
}

.location {
  margin-top: 0.7rem;
}

.price {
  margin-right: 0.2rem;
}

.event-price {
  color: #222222;
  font-size: 14px;
  font-weight: 700;
}

.discount-rate {
  color: #FF5841;
  font-weight: 700;
  font-size: 14px;
}
.original-price {
  color: #919191;
  font-size: 14px;
  font-weight: 400;
  text-decoration-line: line-through;
}

/* side button */
#side-button-container {
  position: fixed;
  top: 50%;
  right: 48px;
}

.showSearchFilter {
  padding-left: 180px;
}


@media screen and (max-width: 1719px){

}

@media screen and (max-width: 1440px){
  #filter-area {
    padding-right: 5em;
  }

}

@media screen and (max-width: 1260px) {
  #filter {
    display: none;
  }
}

@media screen and (max-width: 869px){
  #tattoo-container {
    justify-content: space-evenly;
  }

  .tattoo-board-list {
    width: 350px;
    height: 450px;
  }

  .tattoo-img {
    margin-left: 20px;
    margin-top: 13px;
    width: 310px;
  }
}

@media screen and (max-width: 599px){
  #tattoo-container {
    justify-content: space-around;
  }

  .tattoo-board-list {
    width: 350px;
    height: 450px;
  }

  .tattoo-img {
    margin-left: 10px;
    margin-top: 8px;
    width: 330px;
  }
}
</style>