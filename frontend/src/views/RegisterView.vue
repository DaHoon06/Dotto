<template>
  <article id="register-container">
    <section id="register-title-wrapper">
      <span>회원 가입</span>
    </section>

    <progress-component
      :progress="progress"
    />
    <component
      :is="dynamicComponent"
      @changeComponent="changeComponent"
      @redirectLoginView="redirectLoginView"
      @prev="prev"
      @closeModal="closeForm"
      @redirectHome="redirectHome"
    />

    <section id="register-btn-container" >
      <register-button
        :buttonType="1"
        @redirectLoginForm="redirectLoginView"
      />
      <register-button
        :buttonType="2"
        @changeComponent="changeComponent"
      />
    </section>

  </article>
</template>

<script lang="ts">
import { Component, Emit, Vue } from "vue-property-decorator";
import RegisterComponent from "@/components/member/RegisterComponent.vue";
import CompletedComponent from "@/components/member/CompletedComponent.vue";
import PolicyComponent from "@/components/member/PolicyComponent.vue";
import {
  FooterComponent,
  MenuButton
} from "@/components/common";
import ProgressComponent from "@/components/member/ProgressComponent.vue";
import RegisterButton from "@/components/member/RegisterButton.vue";

@Component({
  components: {
    RegisterComponent,
    FooterComponent,
    MenuButton,
    ProgressComponent,
    RegisterButton
  }
})
export default class RegisterView extends Vue {
  type = 'PolicyComponent';
  progress = 1;

  private changeComponent(componentType: string): void {
    this.type = componentType;
  }
  private prev(componentType: string): void {
    this.type = componentType;
  }


  @Emit('closeModal')
  private closeForm() {
    return true
  }

  @Emit('redirectLoginView')
  private redirectLoginView(LOGIN: string): string {
    return LOGIN;
  }
  @Emit('redirectHome')
  private redirectHome(HOME: string): string {
    return HOME;
  }

  private get dynamicComponent() {
    switch (this.type) {
      case 'PolicyComponent':
        this.progress = 1;
        return PolicyComponent;
      case 'RegisterComponent':
        this.progress = 2;
        return RegisterComponent;
      case 'CompletedComponent':
        this.progress = 3;
        return CompletedComponent;
    }
  }

}
</script>

<style scoped>

#register-container {
  margin:auto;
  height: 100%;

}
#register-title-wrapper {
  display: flex;
  margin-bottom: 41px;

  justify-content: flex-start;
}
#tab-wrapper span {
  margin-left: 0.5em;
  text-shadow: 1px 1px 1px #c9c9c9;
}

.register-tabs span {
  color: #eeeeee;
}

.register-tabs span:nth-child(1) {
  margin-right: 1.3em;
}

.currentPage {
  font-weight: 700;
  color: #606060 !important;
}

#register-btn-container {
  position: absolute;
  top: 90%;
  right: 28%;
}

</style>