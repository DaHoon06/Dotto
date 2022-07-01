import { Module } from "vuex";
import { State } from "@/store";
import { CssControl } from "@/interfaces/common/IOptions";

const cssStore: Module<CssControl, State> = {
    namespaced: true,
    state: {
        SCROLL: false,
    },
    mutations: {
        scrollOn(state, payload) {
            state.SCROLL = payload
        },
        scrollEvent(state, payload) {
            state.SCROLL = payload
        }
    },
    actions: {

    },
    getters: {
        SCROLL: state => state.SCROLL,
    }
}