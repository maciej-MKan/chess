import {create} from "zustand";

const useStore = create((set) => ({
    state: {},
    setState: (newState) => set(() => ({state: newState})),
}));