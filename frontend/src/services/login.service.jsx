import {useNavigate} from "react-router-dom";
import {useEffect} from "react";
import {fetchUserDetails} from "../api/user";
import {useDispatch} from "react-redux";
import {setUsername} from "../redux/userSlice";
import {logIn} from "../redux/authSlice";

export const LoginSuccess = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const onLoginCorrect = (user) => {
        dispatch(setUsername(user.name));
        sessionStorage.setItem("username", user.name);
        dispatch(logIn());
        navigate("/game");
    }

    useEffect(() => {
        fetchUserDetails()
            .then(data => {
                data.name ? onLoginCorrect(data) : navigate("/login");
            })
    }, []);
}