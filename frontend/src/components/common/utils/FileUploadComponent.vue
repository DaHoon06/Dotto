<template>
  <div class="room-file-upload-wrapper">

    <div v-if="!files.length" class="room-file-upload-example-container">
      <div class="room-file-upload-example">
        <div class="room-file-notice-item room-file-upload-button">
          <div class="image-box">
            <img class="upload" src="@/assets/icons/common/upload.png" alt="upload" />
            <label for="file">이미지 첨부</label>
            <input
                type="file"
                id="file"
                ref="fileRef"
                @change="imageUpload"
                multiple
                accept=".jpg, .jpeg, .png"
            />
          </div>
        </div>
      </div>
    </div>
    <div v-else class="file-preview-content-container">
      <div class="file-preview-container">
        <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
          <div class="file-close-button" @click="fileDeleteButton" :id="file.number" >
            x
          </div>
          <img :src="file.preview" />
        </div>
      </div>
      <div class="file-preview-wrapper-upload">
        <div class="image-box">
          <img class="upload" src="@/assets/icons/common/upload.png" alt="upload" />
          <label for="file">이미지 첨부</label>
          <input
              type="file"
              id="file"
              ref="fileRef"
              @change="imageAddUpload"
              multiple
              accept=".jpg, .jpeg, .png"
          />
        </div>
      </div>
    </div>
<!--    <div>-->
<!--      <span>업로드 제한 : 10MB</span>-->
<!--      <span>최대 3장 / JPG, PNG / 10MB까지 등록</span>-->
<!--    </div>-->

  </div>
</template>

<script lang="ts">
import { Component, Ref, Vue } from "vue-property-decorator";
import { IBoard } from "@/interfaces/IBoard";



@Component
export default class FileUploadComponent extends Vue {
  @Ref() readonly fileRef!: any;

  files: IBoard.IFileUpload[] = [];
  uploadImageIndex = 0;

  private imageUpload(): void {
    let num = -1;
    for (let i = 0; i < this.fileRef.files.length; i++) {
      this.files = [
        ...this.files,
        {
          file: this.fileRef.files[i],
          preview: URL.createObjectURL(this.fileRef.files[i]),
          number: i
        }
      ];
      num = i;
    }
    this.uploadImageIndex = num + 1;
  }

  private imageAddUpload() {
    let num = -1;
    for (let i = 0; i < this.fileRef.length; i++) {
      this.files = [
        ...this.files,
        {
          file: this.fileRef.files[i],
          preview: URL.createObjectURL(this.fileRef.files[i]),
          number: i + this.uploadImageIndex
        }
      ];
      num = i;
    }
    this.uploadImageIndex = this.uploadImageIndex + num + 1;
  }

  private fileDeleteButton(e: Event) {
    const target = e.target as HTMLInputElement;
    const id = target.getAttribute('id');
    this.files = this.files.filter((data: IBoard.IFileUpload) => data.number !== Number(id));
  }

}
</script>

<style scoped>
/* 라벨 폰트 사이즈와 동일 크기 */
.upload {
  width: 13px;
  position: relative;
  left: 28px;
}
.room-deal-information-item-wrapper > input {
  border: 1px solid #dddddd;
  width: 140px;
  height: 100%;
  padding: 0 15px;
  font-size: 15px;
}

.room-file-upload-example {
  height: 100%;
}

.file-preview-content-container {
  height: 100%;
}

.room-file-upload-wrapper {
  min-height: 350px;
  font-size: 15px;
  color: #888888;
  display: flex;
  justify-content: flex-start;
  height: 100%;
}

.image-box {
  margin-top: 20px;
}

.image-box input[type='file'] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}

.image-box label {
  display: inline-block;
  padding: 7px 26px 7px 40px;
  background-color: white;
  border: 1px solid #E2E2E2;
  vertical-align: middle;
  font-size: 0.82rem;
  font-weight: 600;
  color: #222222;
  cursor: pointer;
  border-radius: 25px;
}
.image-box label:hover {
  background: #222222;
  color: white;
}

.file-preview-wrapper {
  padding: 10px 0 0 0;
  position: relative;
}

.file-preview-wrapper > img {
  position: relative;
  width: 140px;
  height: 120px;
  z-index: 2;
  margin-right: 10px;
}

.file-close-button {
  position: absolute;
  line-height: 13px;
  z-index: 99;
  font-size: 0.8rem;
  right: 15px;
  top: 15px;
  color: #fff;
  background-color: #222222;
  width: 15px;
  height: 15px;
  text-align: center;
  border-radius: 35px;
  cursor: pointer;
}

.file-preview-container {
  height: 100%;
  display: flex;
  flex-wrap: wrap;
}

.file-preview-wrapper-upload {
  width: 150px;
  height: 90px;
}


.room-write-button-wrapper > div {
  width: 160px;
  height: 50px;
  border: 1px solid #dddddd;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 15px;
  cursor: pointer;
}

</style>