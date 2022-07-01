<template>
  <section id="policy-container">

    <article>
      <div class="policy-items">
        <div>
          <input type="checkbox" id="check" @change="[handleClickAllCheckBox(), btnActive()]" v-model="allCheckItems" />
          <label for="check"></label>
        </div>
        <div id="policy-title" class="policy-title-test">전체 동의 합니다.</div>
      </div>
      <hr class="under-line">
    </article>

    <article >
      <div class="policy-items">
        <div>
          <input type="checkbox" id="check1" @change="[handleClickEachCheckBox(), btnActive()]" v-model="checkList1" />
          <label for="check1"></label>
        </div>
        <div class="policy-title-test">이용약관 동의<span class="necessary">(필수)</span></div>
        <div>
          <img @click="showDottoPolicyContent" class="side-menu-drop-btn filter-text" src="@/assets/icons/nav/filter-btn.svg" alt="sort" />
        </div>
      </div>
      <hr class="under-line">
      <div v-show="showDottoPolicy" class="policy-text" v-html="terms.dottoPolicyContent"></div>
    </article>

    <article >
      <div class="policy-items">
        <div>
          <input type="checkbox" id="check2" @change="[handleClickEachCheckBox(), btnActive()]" v-model="checkList2" />
          <label for="check2"></label>
        </div>
        <div class="policy-title-test">개인정보 수집 . 이용동<span class="necessary">(필수)</span></div>
        <div>
          <img @click="showPrivatePolicyContent" class="side-menu-drop-btn filter-text" src="@/assets/icons/nav/filter-btn.svg" alt="sort" />
        </div>
      </div>
      <hr class="under-line">
      <div v-show="showPrivatePolicy" class="policy-text" v-html="terms.privatePolicyContent"></div>
    </article>

<!--    <article >-->
<!--      <div class="policy-items">-->
<!--        <div>-->
<!--          <input type="checkbox" id="check4" @change="handleClickEachCheckBox" v-model="checkList3" />-->
<!--          <label for="check4"></label>-->
<!--        </div>-->
<!--        <div>이벤트 등 프로모션 알람 메일 수신<span class="choice">(선택)</span></div>-->
<!--        <div>-->
<!--          <img @click="showMarketingPolicyContent" class="side-menu-drop-btn filter-text" src="@/assets/icons/nav/filter-btn.svg" alt="sort" />-->
<!--        </div>-->
<!--      </div>-->
<!--      <hr />-->
<!--      <div v-show="showMarketingPolicy" class="policy-text" v-html="terms.marketingPolicyContent"></div>-->
<!--    </article>-->

    <section id="policy-btn-wrapper" >
      <button class="register-common-btn" type="button" @click="redirectLoginForm">이전</button>
      <button
          class="register-common-btn"
          type="button"
          @click="redirectRegisterForm"
          :disabled="openBtn"
          :class="openBtn ? '' : activeBtn"
      >가입</button>
    </section>

  </section>
</template>

<script lang="ts">
import { Component, Emit, Vue } from "vue-property-decorator";
import termsContent from '@/assets/dummy/terms.ts';

@Component
export default class PolicyComponent extends Vue {
  allCheckItems = false;
  checkList1 = false;
  checkList2 = false;
  checkList3 = false;

  showDottoPolicy = false;
  showPrivatePolicy = false;
  showMarketingPolicy = false;

  openBtn = true;
  activeBtn = 'register-common-btn-active';

  terms = {
    dottoPolicyContent: '',
    privatePolicyContent: '',
    marketingPolicyContent: '',
  }

  constructor() {
    super();
    this.terms = {
      dottoPolicyContent: termsContent[0].dottoPolicyContent,
      privatePolicyContent: termsContent[0].privatePolicyContent,
      marketingPolicyContent: termsContent[0].marketingPolicyContent
    }
  }

  created(): void {
    this.handleClickAllCheckBox();
  }

  private handleClickAllCheckBox() {
    if (this.allCheckItems) {
      this.checkList1 = true;
      this.checkList2 = true;
      this.checkList3 = true;
    } else {
      this.checkList1 = false;
      this.checkList2 = false;
      this.checkList3 = false;
    }
  }

  private btnActive() {
    this.openBtn = !(this.checkList1 && this.checkList2);
  }

  private handleClickEachCheckBox() {
    this.allCheckItems = this.checkList1 && this.checkList2 && this.checkList3;
  }

  private showDottoPolicyContent(): void {
    this.showDottoPolicy = !this.showDottoPolicy;
  }
  private showPrivatePolicyContent(): void {
    this.showPrivatePolicy = !this.showPrivatePolicy;
  }
  private showMarketingPolicyContent(): void {
  this.showMarketingPolicy = !this.showMarketingPolicy;
  }

  @Emit('changeComponent')
  private redirectRegisterForm(): string {
    if (!this.checkList1 || !this.checkList2) {
      alert('필수 선택사항을 체크해 주세요.');
      return 'PolicyComponent';
    } else {
      return 'RegisterComponent';
    }
  }

  @Emit('redirectLoginView')
  private redirectLoginForm(): string {
    return 'Login';
  }

}
</script>

<style scoped>
#policy-container {
  display: flex;
  overflow-y: auto;
  flex-direction: column;
  /*height: 100%;*/
  margin-top: 100px;
}
#policy-container section {
  margin-bottom: 0.6em;
}
#policy-btn-wrapper {
  /*display: flex;*/
  justify-content: space-around;
}
#policy-title {
  text-align: left;
  font-size: 15px;
  font-weight: 600;
}
.policy-title-test {
  margin-left: 10px;
}
.policy-items {
  display: flex;
  justify-content: flex-start;
  align-items: flex-end;
  margin-left: 27px;
}
.policy-items div:nth-child(1) {
  font-size: 14px;
  font-weight: 600;
}

.policy-text {
  text-align: left;
  border: 1px solid #eeeeee;
  height: 100px;
  overflow-y: auto;
  background: #efefef;
  font-size: 12px;
}

.necessary {
  color: #229d17;
  font-size: 12px;
}
.choice {
  color: #949494;
  font-size: 12px;
}
input {
  display: none;
}
input + label {
  display: inline-block;
  width: 20px;
  height: 20px;
  border: 2px solid #dedede;
  cursor: pointer;
  border-radius: 45px;
}
input + label::after {
  content: '✓';
  font-weight: 600;
  color: #dedede;
  font-size: 12px;
  position: relative;
  left: 1px;
  bottom: 5px;
}
input:checked + label::after {
  content: '✓';
  font-weight: 1000;
  color: #1dbe0f;
  font-size: 13px;
  position: relative;
  bottom: 5px;
}
input:checked + label {
  border: 2px solid #1dbe0f;
}

.under-line {
  width: 430px;
  margin-left: 27px;
}

.side-menu-drop-btn-01 {
  width: 18px;
  height: 10px;
  margin-left: 250px;
}

.side-menu-drop-btn-02 {
  width: 18px;
  height: 10px;
  margin-left: 194px;
}
</style>