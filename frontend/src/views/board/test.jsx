import {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {setWaitApi} from "../../redux/gameSlice";


const Test = () => {
    const dispatch = useDispatch();
    const waitApi = useSelector((state) => state.waitApi);
    const doSthElse = (x) => {
        if (!waitApi) {
            return x
        } else {
            setTimeout(() => doSthElse(x), 1000)
        }
    }
    const doSth = () => {
        dispatch(setWaitApi(false));
        doSthElse(123);
    }
    useEffect(() => {
        dispatch(setWaitApi(true))
        doSth();
    }, [])
}