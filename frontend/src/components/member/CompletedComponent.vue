<template>
  <section id="complete_container">
    <article>
      <img id="complete_img" src="@/assets/icons/regist_complete.svg" alt="congratulation">
      <h5>회원가입이 완료되었습니다.!</h5>
    </article>

    <article>
      <button class="register-common-btn" type="button" @click="redirectHome">메인으로</button>
      <button class="register-common-btn" id="register-login-btn" type="button" @click="redirectLogin">로그인</button>
    </article>
  </section>
</template>

<script lang="ts">
import { Component, Emit, Vue } from "vue-property-decorator";

@Component
export default class CompletedComponent extends Vue {

  @Emit('redirectLoginView')
  private redirectLogin(): string {
    return 'Login';
  }
  @Emit('redirectHome')
  private redirectHome(): string {
    return '/dotto';
  }
}
</script>

<style scoped>
#register-login-btn {
  background: #222222;
  color: white;
}
#register-login-btn:hover {
  background: #efefef;
  color: #222222;
}

#complete_img {
  width: 80px;
  height: 80px;
}
</style>