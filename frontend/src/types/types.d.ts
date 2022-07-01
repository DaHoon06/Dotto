import Vue from "vue";

import { Socket } from "socket.io-client";

declare module 'socket.io-client';

declare global {
    interface Window {
        Kakao: any;
    }
}

declare module 'vue/types/vue' {
    interface Vue {
        $gAuth: any;
        $socket: Socket;

        userId: string;
        token: string;
        isLogin: boolean;
    }
}