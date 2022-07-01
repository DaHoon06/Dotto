import { Module } from "vuex";
import { State } from "@/store";
import { IOptions } from "@/interfaces/common/IOptions";

const utilsStore: Module<IOptions, State> = {
    namespaced: true,
    state: {
        isModal: false,
    },
    mutations: {
        showModal(state, payload) {
            state.isModal = payload;
        },
        closeModal(state, payload) {
            state.isModal = payload;
        },

    },
    actions: {

    },
    getters: {
        isModal: state => state.isModal,
    },
}

export default utilsStore;