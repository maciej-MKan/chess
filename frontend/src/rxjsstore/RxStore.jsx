import {BehaviorSubject} from 'rxjs';

const initialState = {key: 'initialValue'};

const state$ = new BehaviorSubject(initialState);

const setState = (newState) => {
    state$.next({...state$.value, ...newState});
};

export {state$, setState};