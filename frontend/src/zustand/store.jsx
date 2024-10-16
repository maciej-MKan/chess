import {create} from "zustand";

export const useStore = create((set) => ({
    testState: {},
    setTestState: (newState) => set(() => ({state: newState})),
}));