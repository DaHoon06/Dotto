import { Component } from 'vue-property-decorator';
import Vue from 'vue';
import store from '@/store';

@Component
export class Mixin extends Vue {
    public $store = store;

    get userId(): string {
        return '';
    }

    get token(): string {
        return '';
    }

    get isLogin(): boolean {
        return this.$store.getters["userStore/isLogin"];
    }

    /* modal */
    get isModal(): boolean {
        return this.$store.getters['utilsStore/isModal'];
    }

    get scrollEvent() {
        return this.$store.getters["utilsStore/SCROLL"];
    }


    cancel(): void {
        return this.$router.go(-1);
    }
}