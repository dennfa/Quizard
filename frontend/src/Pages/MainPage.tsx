import {useNavigate} from "react-router-dom";
import axios from "axios";
import {useEffect, useState} from "react";

export default function MainPage() {

    const navigate = useNavigate();
    const [username, setUsername] = useState<string>("")

    useEffect(() => {
        const storedUsername = localStorage.getItem("username");
        setUsername(storedUsername || "");
    }, []);

    function login() {
        const host = window.location.host === 'localhost:5173' ?
            'http://localhost:8080' : window.location.origin

        window.open(host + '/oauth2/authorization/github')

        setTimeout(()=> {
            whoAmI()
        },1000)
    }

    function logout() {
        axios.post("/api/logout")
            .then(() => {
                setUsername("")
                localStorage.removeItem("username")
            })
            .catch((error) => {
                console.error("Error found", error);
            })
    }

    function whoAmI() {
        axios.get("/api/user")
            .then((response) => {
                setUsername(response.data)
                localStorage.setItem("username", response.data)
            })
            .catch((error) => {
                console.error("Error found", error);
            })
    }

    const isLoggedIn = username !== ""

    return (
        <div className="PageContainer">
            <h1 className="PageHeader">Quizard</h1>
            <div className="MainPageActionContainer">
                <button className="MainPageAction" onClick={() => navigate("/take")}>Take Quiz</button>
                <button className="MainPageAction" onClick={() => navigate("/create")}>Create Quiz</button>
                <button className="MainPageAction" onClick={() => navigate("/update")}>Edit Quiz</button>
            </div>
            {isLoggedIn ? (
                <div className="LoginContainer">
                    <p className="Username">Logged in as {username}</p>
                    <button className="Login" onClick={logout}>Logout</button>
                </div>
            ) : (
                <div className="LoginContainer">
                    <button className="Login" onClick={login}>Login</button>
                </div>
            )}
        </div>
    )
}