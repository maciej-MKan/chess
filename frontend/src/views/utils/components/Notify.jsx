import {toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

export const notify = (message) => {
    console.log(message);
    toast.error(message, {
        position: "top-center",
        autoClose: 3000,
        closeOnClick: true,
    })
}