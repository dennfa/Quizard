import {useNavigate} from "react-router-dom";
import '../Styling/MainPage.css'

export default function MainPage() {

    const navigate = useNavigate();


    return (
        <div className="MainPage">
            <h1>Quizard</h1>
            <div className= "MainPageButtonContainer">
            <button onClick={() => navigate("/take")}>Take Quiz</button>
            <button onClick={() => navigate("/create")}>Create Quiz</button>
            <button onClick={() => navigate("/update")}>Edit Quiz</button>
            </div>
            <button className="Login">Login</button>
        </div>
    )
}