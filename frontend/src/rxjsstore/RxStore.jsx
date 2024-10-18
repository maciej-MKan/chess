import {BehaviorSubject} from 'rxjs';

const initialState = {
    key: 'initialValue',
    boardState: {},
    availableMoves: {}
};

const state$ = new BehaviorSubject(initialState);

const setState = (newState) => {
    console.log("rx store // set new state", newState);
    state$.next({...state$.value, ...newState});
};

export {state$, setState};