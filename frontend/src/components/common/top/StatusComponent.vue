<template>
  <section id="status-container">

    <article id="login-btn-wrapper" v-if="!isLogin">
      <button
          class="nav-icon-label login-label"
          @click="showLoginView">LOGIN</button>
    </article>
    <article id="isLoginTrue" v-else>
      <div id="alarm-wrapper">
        <img id="alarm" src="@/assets/icons/default_alarm.svg" alt="alarm" @click="showAlarm" />
        <div v-if="showMessage" id="alarm-container">
          메세지 영역
        </div>
      </div>
      <div id="my-page-container" >
        <div id="my-page" @click="goMyPage">
          <img src="@/assets/icons/default.svg" id="my" alt="my" />
          <span id="my_name">NAME</span>
        </div>
        <div id="drop-down-btn-container">
          <span @click="showMenu">
            <img src="@/assets/icons/nav/filter-btn.svg" id="drop-down-btn" alt="dropdown" />
          </span>
        </div>
      </div>
      <div v-if="showMyMenu" id="my-menu-list">
        <div id="my-menu-items">
          <div>
            <span>
              <img src="@/assets/icons/mymenu/dottoDesign.svg" alt="design" class="my-menu-icons" />
            </span>
            <router-link to="t">타투도안</router-link>
          </div>
          <div>
            <span>
              <img src="@/assets/icons/mymenu/reservationList.svg" alt="reservation" class="my-menu-icons" />
            </span>
            <router-link to="t">내 예약목록</router-link>
          </div>
          <div>
            <span>
              <img src="@/assets/icons/mymenu/myReview.svg" alt="review" class="my-menu-icons" />
            </span>
            <router-link to="t">내 댓글 / 리뷰</router-link>
          </div>
          <div>
            <span>
              <img src="@/assets/icons/mymenu/favorites.svg" alt="favorites" class="my-menu-icons" />
            </span>
            <router-link to="t">찜한 목록</router-link>
          </div>

          <hr />

          <div>
            <span>
              <img src="@/assets/icons/mymenu/security.png" alt="security" class="my-menu-icons"/>
            </span>
            <router-link to="t">보안 / 계정</router-link>
          </div>
          <div id="logout">

            <button type="button" @click="logout">
              <span>
                <img src="@/assets/icons/mymenu/logout.svg" alt="logout" class="my-menu-icons" />
              </span>
              로그아웃
            </button>
          </div>
        </div>
      </div>
    </article>

    <transition name="fade">
      <main-modal
          :modalType="modalType"
          @modalType="modalType"
          @closeModal="closeModal"
      />
    </transition>

  </section>
</template>

<script lang="ts">
import { Component, Emit, Vue } from "vue-property-decorator";
import MainModal from "@/components/common/utils/modal/MainModal.vue";
import { MODAL } from "@/interfaces/common/ICommon";

@Component({
  components: {
    MainModal
  }
})
export default class StatusComponent extends Vue {
  showSearchList = false;
  modalType = '';
  showMessage = false;
  showMyMenu = false;

  constructor() {
    super();
  }

  private closeModal(payload: boolean) {
    this.$store.commit('utilsStore/showModal', payload);
    this.$store.commit('cssStore/scrollOn', payload);
    this.modalType = MODAL.INIT;
  }

  private showLoginView() {
    this.modalType = MODAL.LOGIN;
    this.$store.commit('utilsStore/showModal', true);
    this.$store.commit('cssStore/scrollOn', true);
  }


  private logout(): void {
    this.$store.commit('userStore/logout');

    // 만약 카카오 로그인일 경우 따로 로그아웃 구현
    this.kakaoLogout();

  }

  private kakaoLogout(): void {
    window.Kakao.API.request({
      url: '/v1/user/unlink',
      success: (res: any) => {
        console.log(res);
      },
      fail: (error: any) => {
        console.log(error);
      }
    })
  }

  private showAlarm(): boolean {
    this.showMessage = !this.showMessage;
    this.showMyMenu = false;
    return this.showMessage
  }
  private showMenu(): boolean {
    this.showMyMenu = !this.showMyMenu;
    this.showMessage = false;
    return this.showMyMenu
  }

  private goMyPage() {
    this.$router.push('/my');
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
#status-container {
  display: flex;
  justify-content: flex-end;
}

/* 로그인 버튼 관련 */
.nav-icon-label {
  color: #919191;
  width: 100%;
  font-size: 16px;
}
.login-label:hover {
  cursor: pointer;
  font-weight: 600;
}
#login-btn-wrapper {
  width: 40px;
  height: 40px;
  margin-right: 46px;
  font-size: 24px;
}



@media screen and (max-width: 1260px) {
  #menu-button-container {
    display: inline;
  }
}

@media screen and (max-width: 869px){
  #search-container {
    display: none;
  }
}
</style>