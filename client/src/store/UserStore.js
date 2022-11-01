import {makeAutoObservable} from "mobx";

export default class UserStore {
    constructor() {
        // this._isAuth = false;
        this._isAuth = true;
        this._user = {};
        makeAutoObservable(this);
    }

    setIsAuth(bool) {
        // this._isAuth = !this._isAuth;
        this._isAuth = bool;
    }

    setUser(user) {
        // this._isAuth = !this._isAuth;
        this._user = user;
    }

    get isAuth() {
        return this._isAuth;
    }

    get isUser() {
        return this._user;
    }

}