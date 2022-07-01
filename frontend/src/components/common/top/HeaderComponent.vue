<template>
  <section id="navBar-container" >

    <article>
      <ul>
        <li id="menu-button-container">
          <button>
            <img class="menu-icon" src="@/assets/icons/nav/menu.png" alt="menu"  />
          </button>
        </li>
        <li>
          <span>
            <a href="/"><img id="logo" src="@/assets/img/dotto.svg" alt="logo" /></a>
          </span>
        </li>
      </ul>
    </article>

    <article id="search-container">
      <input type="text" placeholder="Search" @click="searchLists" id="navigation-search-bar" />
      <button id="search-btn" >
        <img class="side-menu-drop-btn" src="@/assets/icons/nav/search.svg" alt="search" />
      </button>

      <transition name="fade">
        <article v-show="showSearchList" id="search-list">
          <div id="search-list-wrapper">
            <p id="empty-search-lists">검색된 검색어가 존재하지 않습니다.</p>
            <ul>
              <li>
              </li>
            </ul>
          </div>
          <div id="outer" @click="closeSearchList"/>
        </article>
      </transition>
    </article>

    <article class="close-search-list-none" v-if="!showSearchList"></article>
    <article v-else>
      <div class="close-search-list" >
        <button @click="closeSearchList">
          <img src="@/assets/icons/nav/close.png" id="close-img" alt="close" />
        </button>
      </div>
    </article>

  </section>
</template>

<script lang="ts">
import { Component, Emit, Vue } from "vue-property-decorator";
import LoginView from "@/views/LoginView.vue";
import ModalComponent from "@/components/common/utils/modal/ModalComponent.vue";

@Component({
  components: {
    ModalComponent,
    LoginView,
  }
})
export default class HeaderComponent extends Vue {
  showSearchList = false;
  modalType = '';
  showMessage = false;
  showMyMenu = false;

  constructor() {
    super();
  }

  private closeSearchList() {
    this.showSearchList = !this.showSearchList;
    this.$emit('blurBackground', false);
  }

  private logout(): void {
    console.log('logout')
  }

  @Emit('blurBackground')
  private searchLists() {
    this.showSearchList = !this.showSearchList;
    return this.showSearchList
  }

  @Emit('notScroll')
  private notScrollBody() {
    return true;
  }

}
</script>

<style scoped>
#navBar-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 28px;
}

.close-search-list-none {
  margin-right: 40px;
  width: 40px;
  height: 40px;
  padding-top: 3px;
}

/* 로고 */
#logo {
  margin-left: 25px;
  width: 103px;
  height: 40px;
  left: 4px;
  top: 0;
}

#close-img {
  width: 16px;
  height: 16px;
}

/* 검색창 */
#search-container {
  width: 588px;
  height: 40px;
}
#navigation-search-bar {
  width: 90%;
  height: 100%;
  border: 1px solid #ececec;
  outline: none;
  background: #F5F5F5;
  padding-left: 10px;
  padding-right: 10px;
  font-size: 18px;
}
#search-list {
  position: absolute;
  top: 80px;
  left: 0;
  width: 100%;
  max-width: 1980px;
  text-align: center;
}
#search-list-wrapper {
  display: inline-block;
  width: 100vw;
  height: 30vh;
  background: #FFFFFF;
}

#outer {
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.3)
}
#search-btn {
  position: relative;
  right: 35px;
  bottom: 5px;
  width: 24px;
  height: 24px;
}

a {
  text-decoration: none;
  color: #222222;
}

ul li {
  float: left;
  display: flex;
  flex-direction: column;
  align-items: center;
}

hr {
  width: 14vw;
  margin: 1rem -1.5em;
}

a:hover {
  color: #8d8d8d;
  font-weight: 700;

}


</style>