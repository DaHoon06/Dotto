<template>
  <main id="loginPage-container">
    <section id="logo-img" class="login-info-section">
      <span class="logo"><img src="@/assets/img/dotto.svg" alt="logo" /></span>
    </section>

    <section class="login-info-section">
      <form @submit.prevent="login">
        <div>
          <input @focus="clearMsg" class="login-info" type="text" v-model="id" placeholder="아이디" />
        </div>
        <div>
          <input @focus="clearMsg" class="login-info" autocomplete="off" type="password" v-model="password" placeholder="비밀번호" />
        </div>
        <div id="warning-msg" class="text-center">{{ loginFailed }}</div>
        <div id="auto-login-wrapper">
          <input type="checkbox" id="auto" @change="saveId" v-model="save">
          <label for="auto"></label> <span id="auto-login">아이디 저장</span>
        </div>
        <div>
          <button id="login-btn" type="submit">로그인</button>
        </div>
      </form>
    </section>

<!--    <hr />-->
    <br />

<!--  라인 이미지로 대체해야할듯  -->
    <section class="login-info-section">
      <div id="social-login" src="@/assets/icons/board/mymenu/loginSimpleLine.svg">ㅡㅡㅡㅡㅡㅡㅡㅡㅡ간편 로그인ㅡㅡㅡㅡㅡㅡㅡㅡㅡ</div>
<!--      <article id="simple-login-icons">-->
        <div id="login-btn-img">

          <div class="google-btn-section">
            <button id="google-btn" class="social-btn-01">
              <img src="@/assets/img/login/Google.png" alt="google" />
            </button>
          </div>

          <div class="kakao-btn-section">
            <button id="kakao-btn" class="social-btn-02">
              <img src="@/assets/img/login/kakao.png" @click="kakaoLogin" alt="kakao" />
            </button>
          </div>

        </div>
<!--      </article>-->
    </section>

<!--    <hr />-->
    <br />

    <section>
      <div id="register-box">
<!--        <div id="lost-identify">-->
          <div id="lost-password" class="password-section">
<!--            <span>비밀번호를 잊어버리셨나요?</span>-->
            <router-link class="login-router forgot-user-info" to="#"><small>비밀번호 찾기</small></router-link>
          </div>
          <div id="join">
<!--            <span><small>계정이 없다면 바로 가입하세요!</small></span>-->
            <div id="register" class="join-section">
              <button class="login-router" @click="showRegisterView"><small>회원가입 하기</small></button>
            </div>
          </div>
<!--        </div>-->
      </div>
    </section>
  </main>
</template>

<script lang="ts">
import { Vue, Component, Emit } from "vue-property-decorator";
import { IUser } from "@/interfaces/IUser";


@Component({
  components: {
  }
})
export default class LoginView extends Vue {
  id = '';
  password = '';
  save = false;
  loginFailedMsg = '';
  modalTypeRegister = '';
  // $gAuth: any;

  params = {
    client_id: process.env.VUE_APP_GOOGLE_KEY
  }

  constructor() {
    super();
  }

  created(): void {
    this.getCookie();
  }

  async login(): Promise<void> {
    const sendData: IUser.ILoginProp = {
      id: this.id,
      password: this.password
    };
    const { data } = await this.axios.post('/sign-in', sendData) as { data: IUser.ILoginSuccess }
    const { success } = data;
    if (success) {
      if (this.save) this.saveId();
      this.saveState(data);
      await this.$router.push({
        path: '/dotto'
      });
    } else {
      this.loginFailed = '이메일 및 패스워드를 확인해주세요.';
    }
  }

  saveId(): void{
    if(this.save) {
      this.$cookies.set('idCookie', this.id,'30d');
    } else {
      this.$cookies.remove('idCookie');
      this.id = '';
    }
  }

  getCookie(): void {
    if (this.$cookies.isKey('idCookie')){
      this.id = this.$cookies.get('idCookie');
      this.save = true;
    }
  }

  saveState(userData: IUser.ILoginSuccess): void {
    this.$store.commit('userStore/login', userData);
  }

  clearMsg(): void {
    this.loginFailed = '';
  }


  private kakaoLogin(): void {
    window.Kakao.init(process.env.VUE_APP_KAKAO_KEY);

    if (window.Kakao.Auth.getAccessToken()) {
      window.Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response: Response) {
          console.log(response)
        },
        fail: function (error: Error) {
          console.log(error)
        },
      })
      window.Kakao.Auth.setAccessToken(undefined)
      window.Kakao.Auth.login({
        success: () => {
          window.Kakao.API.request({
            url: '/v2/user/me',
            data: {
              property_keys: ["kakao_account.email"]
            },
            success: async (response: Response) => {
              console.log(response);
            },
            fail: (error: Error) => {
              console.log(error)
            },
          })
        },fail: (error: Error) => {
          console.log(error)
        }
      })
    }
  }

  @Emit('modalTypeRegister')
  private showRegisterView() {
    return this.modalTypeRegister = 'RegisterView';
  }

  private set loginFailed(msg) {
    this.loginFailedMsg = msg;
  }
  private get loginFailed() {
    return this.loginFailedMsg;
  }

}
</script>

<style scoped>
hr {
  width: 80%;
}

#loginPage-container {
  display: flex;
  max-width: 400px;
  margin: 45px auto 45px auto;
  flex-direction: column;
  align-items: center;
}

.login-info {
  background: #fafafa;
  outline: none;
  width: 100%;
  border: 1px solid #eeeeee;
  height: 30px;
  font-size: 11px;
  padding-left: 5px;
  margin-bottom: 10px;
  border-radius: 2px;
}

.login-info-section {
  width: 75%;
  margin: auto;
}

#logo-img {
  margin-bottom: 30px;
  text-align: center;
}

#logo-img img {
  width: 120px;
  margin-bottom: 10px;
}

input[type="checkbox"] {
  display: none;
}

input[type="checkbox"] + label{
  display: inline-block;
  width: 15px;
  height: 15px;
  border:1px solid #919191;
  position: relative;
}

input[id="auto"]:checked + label::after{
  content: '✓';
  color: #05152f;
  font-weight: 800;
  font-size: 12px;
  padding-bottom: 3px;
  width: 10px;
  text-align: center;
  position: absolute;
  left: 1px;
  top: -3px;
}

#auto-login {
  font-size: 9px;
  margin-left: 5px;
}
#auto-login-wrapper {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 10px;
}

#login-btn {
  border: 1px solid gray;
  border-radius: 3px;
  color: white;
  font-size: 12px;
  padding: 5px;
  background: #222222;
  width: 100%;
}

#login-btn-img {
  display: flex;
  /*position: relative;*/
  left: -10px;
}

#login-btn:hover{
  background: #464646;
  color: #ffffff;
}
#login-btn-img > div {
  /*margin-left: 20px;*/
}
#register-box {
  display: flex;
  justify-content: space-evenly;
}

#register > a {
  margin-right: 10px;

}

.login-router, #social-login {
  color: #919191;
  font-size: 12px;
  text-decoration: none;

}
.login-router:hover {
  font-size: 13px;
  font-weight: bold;
  cursor: pointer;
  color: #565656;
}
.forgot-user-info {

}

#social-login {
  text-align: center;
  margin-bottom: 10px;
}

#simple-login-icons {
  display: flex;
  justify-content: center;
}

#login-btn-img > li {
  float: left;

}

.social-btn-01 {
  border: 1px solid #eeeeee;
  border-radius: 3px;
  width: 255px;
  height: 30px;
  padding: 1px 0;
}
.social-btn-02 {
  border: 1px solid #eeeeee;
  border-radius: 3px;
  width: 255px;
  height: 30px;
  margin-top: 40px;
  padding: 1px 0;
}
#google-btn {
  background: #F5F5F5;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
#google-btn::after {
  content: '구글 계정으로 로그인';
  font-size: 7px;
  color: #888888;
  font-weight: 600;
}
#kakao-btn {
  background: #fddc3f;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}
#kakao-btn::after {
  content: '카카오 계정으로 로그인';
  font-size: 7px;
  color: #595959;
  font-weight: 600;
}
#login-btn-img > div > button > img {
  width: 25px;
  position: relative;
}
#lost-password, #join {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 25px;
  width: 5em;
}
#lost-password > span,  #join > span {
  font-size: 12px;
}
#warning-msg {
  color: red;
  font-size: 6px;
}

.google-btn-section {
  width: 0px;
  height: 0px;
}

.kakao-btn-section {
  width: 0px;
  height: 0px;
}

.password-section {
  /*!important;*/
  margin-top: 70px;
  margin-left: 10px;
}

.join-section {
  margin-top: 136px;
  margin-left: 10px;
}

</style>
