export namespace IUser {
    export interface IRegisterProp{
        nickname: string,
        password: string,
        id: string;
        phone: string;
        gender: string;
    }

    export interface ITattoist extends IRegisterProp{
        addr: string;
        subAddr: string;
    }

    export interface ILoginProp {
        id: string,
        password: string
    }

    export interface ILoginSuccess {
        success: boolean,
        code: number,
        result: DataProperty,
        roles: string[]
    }

    export interface DataProperty {
        accessToken: string,
        refreshToken: string,
        nickname: string,
        roles: string[]
    }


    export interface UserStore {
        isLogin: boolean,
        accessToken: string,
        refreshToken: string,
        nickname: string,
        roles: string,
    }

    export interface AxiosLoginResponse {
        success: boolean,
        result: IUser.DataProperty,
        code: number,
    }


}