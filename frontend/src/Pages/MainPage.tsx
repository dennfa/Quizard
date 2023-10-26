import {useNavigate} from "react-router-dom";

export default function MainPage() {

    const navigate = useNavigate();

    const handleCreateQuizClick = ()=>{
        navigate("/create");
    }

    return (
            <div className="MainPage">
                <button>Take a Quiz</button>
                <button onClick={handleCreateQuizClick}>Create a Quiz</button>
                <button>Update a Quiz</button>
                </div>
    )
}