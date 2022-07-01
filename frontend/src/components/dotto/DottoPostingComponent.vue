<template>
  <div id="dotto-post-wrapper">

    <div id="dotto-post-btn-area">
      <button
          type="button"
          id="dotto-post-btn"
          @click="posting"
          :disabled="openBtn"
          :class="openBtn ? '' : activeBtn">등록하기</button>
    </div>
    <div id="dotto-post-body">
      <div id="dotto-post-items-wrapper">
        <div id="dotto-post-title" class="dotto-post-side-div">
          <h4>닷투 게시글 작성</h4>
        </div>

        <div class="dotto-post-side-div">
          <input @keyup="validation" v-model="title" class="dotto-post-input" type="text" placeholder="ex) 블랙워크 꽃과 나비" />
        </div>

        <div class="dotto-post-side-div">
          <select @keyup="validation" v-model="genreDefault" class="select">
            <option disabled value="">장르</option>
            <option v-for="(value, index) in genre" :value="value.value" :key="index">{{ value.text }}</option>
          </select>
          <select @keyup="validation" v-model="totalTimeDefault" class="select">
            <option disabled value="">소요시간</option>
            <option v-for="(value, index) in totalTime" :value="value.value" :key="index">{{ value.text }}</option>
          </select>
        </div>

        <hr />
        <div class="event-price-wrapper dotto-post-side-div">
          <div class="won">
            <input
                @keyup="validation"
                v-model="originalPrice"
                id="original-price"
                class="dotto-post-input"
                type="number"
                placeholder="가격" /> <span>원</span>
            <div id="event-price-radio-area">
              <input v-model="salesYn" type="checkbox" id="event-price" />
              <label for="event-price">할인이벤트</label>
            </div>
          </div>
        </div>

        <transition name="fade">
          <div class="event-price-wrapper dotto-post-side-div" v-show="salesYn">
            <div>
              <label>정상가격</label>
              <div class="won">
                <input v-model="price" class="dotto-post-input" type="number" placeholder="0" /> <span>원</span>
              </div>
            </div>
            <div>
              <label>할인가격</label>
              <div class="won">
                <input v-model="salesPrice" class="dotto-post-input" type="number" placeholder="0" /> <span>원</span>
              </div>
            </div>
          </div>
        </transition>

        <hr />

        <div id="textarea-wrapper" class="dotto-post-side-div">
          <div id="dotto-post-tags-wrapper">
            <div v-for="(tags, index) in tag" :key="index">
              <span class="dotto-post-tags" >
                {{ tags }}
                <button @click="deleteTag(index)">X</button>
              </span>
            </div>
            <input v-model="addTag" maxlength="10" id="dotto-post-tags" type="text" @keypress.enter="addTags" placeholder="해쉬 태그 등록" />
          </div>
          <div>
            <textarea @keyup="validation" v-model="content" placeholder="내용을 입력해 주세요.."></textarea>
          </div>
        </div>

        <file-upload-component />

      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Vue } from "vue-property-decorator";
import { IBoard } from "@/interfaces/IBoard";
import FileUploadComponent from "@/components/common/utils/FileUploadComponent.vue";
import { ins } from '@/lib/axios';
import {AxiosInstance} from "axios";
@Component({
  components: {
    FileUploadComponent
  }
})
export default class DottoPostingComponent extends Vue {

  title = '';
  content = '';
  originalPrice = 0;
  price = 0;
  salesPrice = 0;
  salesYn = false;
  genreDefault = '';
  totalTimeDefault = '';
  postPhoto: any = [];

  tag: string[] = [];
  addTag = '';

  genre = [
    { text: '1', value: '1' },
    { text: '2', value: '2' },
    { text: '3', value: '3' },
    { text: '4', value: '4' },
    { text: '5', value: '5' },
  ];
  totalTime = [
    { text: '1', value: '1' },
    { text: '2', value: '2' },
    { text: '3', value: '3' },
    { text: '4', value: '4' },
    { text: '5', value: '5' },
  ];
  openBtn = true;
  activeBtn = 'active-post-btn';

  created(): void {
    this.changeBackground();
  }

  private validation(): void {
    this.openBtn = !(this.title.length && this.genreDefault.length && this.totalTimeDefault.length && this.content.length && this.originalPrice);
  }


  async posting(): Promise<void> {
    console.log(this.originalPrice)
    const sendData: any = {
      title: this.title,
      content: this.content,
      price: +this.originalPrice,
      salesPrice: this.salesPrice,
      salesYn: this.salesYn,
      genre: this.genreDefault,
      totalTime: this.totalTimeDefault,
      tag: this.tag,
      postPhoto: this.postPhoto
    }

    const headers = {
      'Content-Type': 'multipart/form-data',
    }
    const test = new FormData();
    test.append('data', sendData);
    console.log(sendData)
    const { data } = await ins.post('/dottopost', test, {
      headers: {
        'Content-Type': 'multipart/form-data',
      }
    });
    console.log(data);
  }

  private uploadFiles(e: Event) {
    const target = e.target as HTMLInputElement;
    this.postPhoto = target.files;
    this.addFiles(this.postPhoto);
  }
  private async addFiles(files: File[]): Promise<void> {
    const fileList = new FormData();
    for (let i = 0; i < files.length; i++) {
      fileList.append('files', this.postPhoto[i]);
    }
  }

  private addTags(): void {
    if (this.tag.length < 5) this.tag.push(this.addTag);
    else alert('모달 새로만들어서 최대 태그 문구 화면에 출력');

    this.addTag = '';
  }

  private deleteTag(index: number): void {
    this.tag.splice(index, 1);
  }

  @Emit('changeBackground')
  private changeBackground() {
    return 'posting';
  }

}
</script>

<style scoped>
hr {
  width: 100%;
  margin: 2rem auto;
  z-index: 0;
}

textarea {
  width: 90%;
  padding: 1rem;
  height: 400px;
  border: none;
  outline: none;
  resize: none;
  font-size: 14px;
  margin-left: 1.5rem;
}

.select {
  border: 1px solid #E2E2E2;
  width: 30%;
  height: 35px;
  padding: 5px 30px 5px 5px;
  border-radius: 4px;
  outline: none;
  margin: 20px 20px 0 0;
  color: #BDBDBD;
}
.select option {
  background: #ffffff;
  color: #000000;
  padding: 3px 0;
}

#dotto-post-items-wrapper {
  margin: auto;
  width: 90%;
}
#dotto-post-title {
  margin: 5em 0 2em 0;
}
#dotto-post-wrapper {
  height: 100%;
}

#dotto-post-btn-area {
  position: fixed;
  top: 144px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  width: 100%;
  height: 50px;
  border-bottom: 1px solid #BDBDBD;
  background: white;
  z-index: 1;
}

#dotto-post-btn {
  border: 1px solid white;
  border-radius: 30px;
  margin-right: 4em;
  width: 130px;
  height: 40px;
  background: #F5F5F5;
  color: #BDBDBD;
  font-size: 14px;
}

/* 게시글 작성 부분 */
#dotto-post-body {
  margin: auto;
  width: 90%;
  max-width: 1200px;
  height: 100%;
  max-height: 1325px;
  min-height: 600px;
  display: flex;
  background: white;
}

.dotto-post-side-div {
  width: 100%;
}

#event-price-radio-area {
  margin-left: 2rem;
}

#textarea-wrapper {
  border: 1px solid #E2E2E2;
  width: 100%;
  margin-top: 3rem;
  border-radius: 3px;
}
#dotto-post-tags {
  width: 95%;
  min-width: 20em;
  border: none;
  margin: auto;
  border-bottom: 1px solid #BDBDBD;
  outline: none;
  padding: 1rem;
  font-size: 13px;
}

/* 가격 관련 태그  */
#original-price {
  width: 25%;
}

.event-price-wrapper {
  display: flex;
}

.dotto-post-input {
  border: 1px solid #E2E2E2;
  border-radius: 3px;
  height: 40px;
  color: #BDBDBD;
  outline: none;
  width: 100%;
  padding: 3px;
}
.won {
  display: flex;
  width: 90%;
  align-items: center;
}

.dotto-post-tags {
  display: flex;
  justify-content: space-evenly;
  font-size: 14px;
  border-radius: 3px;
  color: #222222;
  margin: 20px 10px 10px 5px;
  background: #F5F5F5;
  padding: 5px 7px;
}
.dotto-post-tags > button {
  margin-left: 10px;
}

#dotto-post-tags-wrapper {
  overflow: auto;
  display: flex;
}


</style>