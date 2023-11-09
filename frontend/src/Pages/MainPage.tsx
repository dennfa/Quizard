import {useNavigate} from "react-router-dom";
import '../Styling/MainPage.css'

export default function MainPage() {

    const navigate = useNavigate();


    return (
        <div className="PageContainer">
            <h1 className="PageHeader">Quizard</h1>
            <div className= "MainPageActionContainer">
            <button className="MainPageAction" onClick={() => navigate("/take")}>Take Quiz</button>
            <button className="MainPageAction" onClick={() => navigate("/create")}>Create Quiz</button>
            <button className="MainPageAction" onClick={() => navigate("/update")}>Edit Quiz</button>
            </div>
            <div className="LoginContainer">
            <button className="Login">Login</button>
            </div>
        </div>
    )
}