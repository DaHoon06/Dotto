<template>
  <section id="member-register-wrapper" >
    <form id="member-register-form" @submit.prevent="register">
      <div class="input-wrapper">
        <label>아이디</label>
        <input
            @keydown.enter.prevent
            @blur="validationId"
            @change="btnActive"
            type="text"
            v-model="id"
            class="input-text"
            ref="refId"
        />
        <button class="register-btn" @click="idCheck" type="button">중복확인</button>
      </div>

      <div class="warning-msg">{{ IdMessage }}</div>

      <div class="input-wrapper">
        <label>비밀번호</label>
        <input
            @keydown.enter.prevent
            type="password" autocomplete="off"
            @change="[validationPassword(), btnActive()]"
            v-model="password"
            class="input-text"
            ref="refPassword"
        />
      </div>
      <div class="warning-msg">{{ PasswordMessage }}</div>

      <div class="input-wrapper">
        <label>비밀번호 확인</label>
        <input
            @keydown.enter.prevent
            type="password" autocomplete="off"
            @change="[validationPassword(), btnActive()]"
            v-model="passwordCheck"
            class="input-text" />
      </div>
      <div class="warning-msg">{{ PasswordCheckMessage }}</div>

      <div class="input-wrapper">
        <label>닉네임</label>
        <input
            @keydown.enter.prevent
            type="text"
            @focus="msgClear"
            @change="btnActive"
            v-model="nickname"
            class="input-text"
            ref="refNickName"
        />
        <button
            @click="validationNickName"
            class="register-btn"
            type="button">중복확인</button>
      </div>
      <div class="warning-msg">{{ NickNameMessage }}</div>

      <div class="input-wrapper" >
        <input
            @keydown.enter.prevent
            @keydown="validationPhoneNumber"
            @change="btnActive"
            type="number"
            maxlength="11"
            class="input-text"
            v-model="phone"
            placeholder="휴대폰번호 입력"
            ref="refPhone"
        />
        <button class="register-btn">인증번호받기</button>
      </div>
      <div class="warning-msg">{{ PhoneMessage }}</div>

      <div class="input-wrapper">
        <input
            @keydown.enter.prevent
            type="text"
            class="input-text"
            placeholder="인증번호 입력" />
        <button class="register-btn">인증하기</button>
      </div>

      <div id="gender-wrapper">
        <input @keydown.enter.prevent class="selected-gender" @change="btnActive" type="radio" id="male" v-model="gender" value="male" name="male" />
        <label for="male">남성</label>
        <input @keydown.enter.prevent class="selected-gender" @change="btnActive" type="radio" id="female" v-model="gender" value="female" name="female" />
        <label for="female">여성</label>
      </div>

      <div class="register-submit">
        <button class="register-common-btn" type="button" @click="prev">이전</button>
        <button
            class="register-common-btn"
            type="submit"
            :disabled="openBtn"
            :class="openBtn ? '' : activeBtn"
        >다음</button>
      </div>
    </form>
  </section>
</template>

<script lang="ts">
import { Component, Emit, Prop, Ref, Vue } from "vue-property-decorator";
import { IUser } from "@/interfaces/IUser";
import { EMessageRegister } from "@/interfaces/common/EMessageType";

@Component
export default class MemberRegisterComponent extends Vue {
  @Prop() type?: string;

  @Ref() readonly refId!: HTMLElement;
  @Ref() readonly refPassword!: HTMLElement;
  @Ref() readonly refNickName!: HTMLElement;
  @Ref() readonly refPhone!: HTMLElement;

  id = '';
  password = '';
  passwordCheck = '';
  nickname = '';
  phone = '';
  //TODO: 인증 후 true로
  authentication = true;
  // ERROR MSG
  nickNameMessage = '';
  passwordMessage = '';
  passwordCheckMessage = '';
  idMessage = '';
  phoneMessage = '';
  gender = '';
  openBtn = true;
  activeBtn = 'register-common-btn-active';

  constructor() {
    super();
  }

  private btnActive() {
    this.openBtn = !(this.id.length && ( this.phone.length === 11 ) && (this.password === this.passwordCheck) && this.nickname.length && this.authentication && this.gender.length);
  }

  private validationId(): void {
    const reg =  /^[a-z0-9_]{4,20}$/;

    if (!reg.test(this.id)) {
      this.IdMessage = EMessageRegister.ID_NOT_ENTERED;
      this.id = '';
    } else {
      this.IdMessage =  EMessageRegister.BLANK;
    }
  }

  async idCheck(): Promise<void> {
    const { data } = await this.axios.get('/members/readId', {
      params: {
        id: this.id,
      }
    })
    const { result } = data;
    if (result) {
      this.IdMessage =  EMessageRegister.AVAILABLE_ID;
    } else {
      this.IdMessage = EMessageRegister.EXIST_ID;
    }
    console.log(result);
  }

  async nickNameCheck(): Promise<void> {
    const { data } = await this.axios.get('/members/readNickName',{
      params: {
        nickname: this.nickname,
      }
    });
    const { result } = data;
    if (result) {
      this.NickNameMessage = EMessageRegister.BLANK;
    } else {
      this.NickNameMessage = EMessageRegister.EXIST_NICKNAME;
    }
  }
  validationNickName(): void {
    const reg = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
    if (reg.test(this.nickname)) {
      this.NickNameMessage = EMessageRegister.SPECIAL_CHARACTERS_NOT_ALLOWED;
    } else {
      this.NickNameMessage = EMessageRegister.BLANK;
      this.nickNameCheck();
    }
  }

  validationPhoneNumber(): void {
    const reg = /^01([016789])-?([0-9]{3})-?([0-9]{4})$/;
    if (reg.test(this.phone)) {
      this.PhoneMessage = EMessageRegister.BLANK;
    } else {
      if (this.phone.length === 10) {
        this.PhoneMessage = EMessageRegister.BLANK;
      } else {
        this.PhoneMessage = EMessageRegister.PHONE_NOT_ENTERED;
        this.openBtn = true;
      }
    }
  }

  validationPassword(): void {
    const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
    if(!reg.test(this.password) && this.password.length) this.PasswordMessage = EMessageRegister.PASSWORD_NOT_ENTERED;
    else this.PasswordMessage = EMessageRegister.BLANK;

    if (this.password !== this.passwordCheck) this.PasswordCheckMessage = EMessageRegister.PASSWORD_RECHECK;
    else this.PasswordCheckMessage = EMessageRegister.BLANK;
  }

  private blankCheck(): boolean {
    let result = false
    if (!this.id.length) {
      this.$nextTick(() => this.refId.focus());
    } else if (!this.password.length) {
      this.$nextTick(() => this.refPassword.focus());
    } else if (!this.nickname.length) {
      this.$nextTick(() => this.refNickName.focus()); return false;
    } else if (!this.phone.length) {
      this.$nextTick(() => this.refPhone.focus()); return false;
    } else {
      result = true;
    }
    return result
  }

  async register(): Promise<void> {
    const result = this.blankCheck();
    //TODO: 휴대폰 인증에 관한 처리
    if (result && this.authentication) {
      const sendData: IUser.IRegisterProp = {
        nickname: this.nickname,
        password: this.password,
        id: this.id,
        phone: this.phone,
        gender: this.gender
      };

      //TODO: 반환 data에 대한 타입 정의
      const { data } = await this.axios.post('/sign-up', sendData) as { data: any };
      const { success } = data;
      if (success) {
        this.changeComponent(success);
      } else {
        this.changeComponent(success);
      }
    } else {
      //TODO : 추후 수정
      alert('빈 곳이 있다는 모달 ');
    }
  }

  //TODO : 추후 수정 !success -> success 로 변경 예정
  @Emit('changeComponent')
  private changeComponent(success: boolean): string {
    if (!success) {
      return 'CompletedComponent';
    } else {
      return 'RegisterComponent';
    }
  }

  @Emit('prev')
  private prev(): string {
    return 'PolicyComponent';
  }

  msgClear(): void{
    if(this.NickNameMessage !== EMessageRegister.AVAILABLE_NICKNAME) {
      this.NickNameMessage = EMessageRegister.BLANK;
      this.nickname = '';
    }
  }

  private set NickNameMessage(msg: string) {
    this.nickNameMessage = msg
  }
  private get NickNameMessage() {
    return this.nickNameMessage;
  }
  private set PasswordMessage(msg: string) {
    this.passwordMessage = msg;
  }
  private get PasswordMessage() {
    return this.passwordMessage;
  }
  private set PasswordCheckMessage(msg: string) {
    this.passwordCheckMessage = msg;
  }
  private get PasswordCheckMessage() {
    return this.passwordCheckMessage;
  }
  private set IdMessage(msg: string) {
    this.idMessage = msg;
  }
  private get IdMessage() {
    return this.idMessage;
  }
  private set PhoneMessage(msg: string) {
    this.phoneMessage = msg;
  }
  private get PhoneMessage() {
    return this.phoneMessage;
  }


}
</script>

<style scoped>
#member-register-wrapper {
  height: 90%;
}

#member-register-form {
  height: 95%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  margin: 0 2em;
}

.register-submit {
  margin-top: 20px;
  width: 100%;
}

select::-ms-expand {
  display: none;
}
.select {
  -o-appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  width: 150px;
  height: 35px;
  background: url('https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png') calc(100% - 5px) center no-repeat;
  background-size: 20px;
  padding: 5px 30px 5px 10px;
  border-radius: 4px;
  outline: 0 none;
}

.select option {
  background: black;
  color: #fff;
  padding: 3px 0;
}
</style>