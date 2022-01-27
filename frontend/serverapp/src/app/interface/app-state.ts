import { DataState } from "../enum/data-state.enum";

export interface AppState<T>{
    dataState: DataState;
    appData?: T; // T meaning any type of Data can pass
    error?: string;
};
