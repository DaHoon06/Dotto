import { Module } from 'vuex';
import { State } from '@/store';
import { ins as axios } from "@/lib/axios";
import { IUser } from "@/interfaces/IUser";


const userStore: Module<IUser.UserStore, State> = {
    namespaced: true,
    state: {
        isLogin: false,
        accessToken: '',
        refreshToken: '',
        nickname: '',
        roles: '',
    },
    mutations: {
        login(state, payload){
            const { accessToken, refreshToken, nickname, roles } = payload;
            state.isLogin = true;
            state.accessToken = accessToken;
            state.refreshToken = refreshToken;
            state.nickname = nickname;
            state.roles = roles;
        },
        logout(state){
            state.isLogin = false;
            state.accessToken = '';
            state.refreshToken = '';
            state.nickname = '';
            state.roles = '';
        },
    },
    actions: {
        async login(context, { token }): Promise<boolean>{
            try {
                const { data } = await axios.post<IUser.AxiosLoginResponse>(`/user/auth/${token}`);
                const { success, result } = data;
                const { accessToken, refreshToken, nickname, roles } = result;
                const role = roles[0];
                if (success) {
                    context.commit('login', {
                        nickname,
                        accessToken,
                        refreshToken,
                        roles: role
                    });
                    return true;
                } else {
                    return false
                }
            } catch (e) {
                console.log(e);
                return false;
            }
        },
        async verify(context, { token }) {
            try {
                const { data } = await axios.post<IUser.AxiosLoginResponse>(`/user/verify`, token);
                const { success, result } = data;
                const { accessToken, refreshToken, nickname } = result;
                if (success) {
                    context.commit('login', {
                        nickname,
                        accessToken,
                        refreshToken,
                    });
                    return true;
                } else {
                    return false;
                }
            } catch (e) {
                console.log(e);
                return false;
            }
        },
        logout(context){
            context.commit('logout');
        },
    },
    getters: {
        isLogin: state => state.isLogin,
        nickname: state => state.nickname,
        accessToken: state => state.accessToken,
        refreshToken: state => state.refreshToken,
        roles: state => state.roles,
    },
}
export default userStore;