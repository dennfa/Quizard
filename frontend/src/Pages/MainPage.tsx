import {useNavigate} from "react-router-dom";

export default function MainPage() {

    const navigate = useNavigate();


    return (
            <div className="MainPage">
                <button onClick={()=>navigate("/take")}>Take a Quiz</button>
                <button onClick={()=>navigate("/create")}>Create a Quiz</button>
                <button>Update a Quiz</button>
                </div>
    )
}