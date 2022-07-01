<template>
  <nav id="navigation-container">
    <ul>
      <li>
        <article class="menu">
          <ul>
            <li>
              <router-link to="/"
                           class="nav-button"
                           :class="navigationType === 'home' ? currentPage : ''" >
                HOME</router-link>
            </li>
          </ul>
        </article>
      </li>
      <li>
        <article class="menu">
          <ul>
            <li>
              <router-link
                  to="/"
                  class="nav-button"
                  :class="navigationType === 'try' ? currentPage : ''">
                try DOTTO</router-link>
            </li>
          </ul>
        </article>
      </li>
      <li>
        <article class="menu">
          <ul>
            <li>
              <span
                  class="nav-button"
                  :class="navigationType === 'community' ? currentPage : ''"
                  @mouseover="showCommunity('show')"
                  @mouseleave="showCommunity('hide')">COMMUNITY</span>
            </li>
            <li v-show="menu_community">
              <div class="nav-menu-list"
                   @mouseover="showCommunity('show')"
                   @mouseleave="showCommunity('hide')">
                <router-link
                    to="/dotto/board/index"
                    class="sub-nav-items"
                >닷투 게시판</router-link>
                <router-link
                    to="/"
                    class="sub-nav-items"
                >닷찾사 게시판</router-link>
              </div>
            </li>
          </ul>
        </article>
      </li>
      <li>
        <article class="menu">
          <ul>
            <li>
              <router-link
                  class="nav-button"
                  :class="navigationType === 'feed' ? currentPage : ''"
                  to="/dotto/feed/index"
              >FEED</router-link>
            </li>
          </ul>
        </article>
      </li>
      <li>
        <article class="menu">
          <ul>
            <li>
              <span
                  class="nav-button"
                  :class="navigationType === 'support' ? currentPage : ''"
                  @mouseover="showInformation('show')"
                  @mouseleave="showInformation('hide')">고객지원</span>
            </li>
            <li v-show="menu_information">
              <div class="nav-menu-list"
                   @mouseover="showInformation('show')"
                   @mouseleave="showInformation('hide')">
                <router-link
                    to="/"
                    class="sub-nav-items"
                >FAQ</router-link>
                <router-link
                    to="/"
                    class="sub-nav-items"
                >공지사항</router-link>
              </div>
            </li>
          </ul>
        </article>
      </li>
    </ul>
  </nav>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class NavigationComponent extends Vue {
  @Prop() navigationType?: string;

  menu_information = false;
  menu_community = false;
  currentPage = 'current-page';

  navType = '';

  constructor() {
    super();
  }

  private showInformation(event: string) {
    this.menu_information = event === 'show';
  }

  private showCommunity(event: string) {
    this.menu_community = event === 'show';
  }

}
</script>

<style scoped>
#navigation-container {
  display: flex;
  width: 100%;
  height: 100%;
  align-items: center;
  margin-bottom: 15px;
}
#navigation-container > ul {
  padding-left: 17px;
}

#navigation-container > ul > li {
  float: left;
  margin-right: 20px;
}

.nav-menu-list {
  background: rgba(255, 255, 255, 0.9);
  width: 130px;
  height: 80px;
  position: absolute;
  margin-left: -1.3rem;
  color: #ffffff;
  text-align: center;
  border-radius: 2px;
  z-index: 10;
  font-size: 12px;
}

.sub-nav-items:hover {
  cursor: pointer;
  font-size: 12px;
  background: rgba(250, 250, 250, 0.7);
  border-radius: 2px;
  padding: 3px 10% 3px 10%;
  color: #222222;
}

.nav-button {
  color: #919191;
  padding-left: 5px;
}

.nav-button:hover {
  cursor: pointer;
  color: #919191;
}

.nav-button:hover {
  color: #222222;
}

.menu > ul > li {
  float: none;
  margin-right: -15px;
}

.sub-nav-items {
  color: #919191;
  display: block;
  text-decoration: none;
  margin-top: 10px;
  margin-bottom: 10px;
}

/* 아래 항목 : 이벤트에 따른 CSS 적용 */
.current-page {
  color: #222222;
  font-weight: bold;
  text-shadow: 1px 1px #a9a9a9;
}

</style>