import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {PlayMultipleChoiceQuiz} from "../Models/play/PlayMultipleChoiceQuiz.tsx";
import PlayQuizCard from "../Components/play/PlayQuizCard.tsx";
import BackIcon from "../Assets/back.svg"

export default function TakeQuizList() {

    const [playMultipleChoiceQuizzes, setPlayMultipleChoiceQuizzes] = useState<PlayMultipleChoiceQuiz[]>([])
    const navigate = useNavigate()

    useEffect(()=>
    {
        axios.get("/api/take")
            .then(response => {
                setPlayMultipleChoiceQuizzes(response.data)
            })
            .catch(error => {
                console.error("Error during quiz loading: ", error)
            })
    },[])

    return (
        <div className="PageContainer">
            <h2 className="PageHeader">Take Quiz</h2>
            <img
                className="BackButton"
                onClick={()=>navigate("/")}
                src={BackIcon}
                alt="Back Icon"
                onKeyDown={(event) => {
                    if (event.key === 'Enter') {
                        navigate("/");
                    }
                }}
                tabIndex={0}
            />
            {playMultipleChoiceQuizzes.map(quiz=>
                <div
                    className="QuizListContainer"
                    key = {quiz.id}
                    onClick={()=>navigate("" + quiz.id)}
                    onKeyDown={(event) => {
                        if (event.key === 'Enter') {
                            navigate("" + quiz.id);
                        }
                    }}
                    tabIndex={1}
                >
                    <PlayQuizCard key={quiz.id} playMultipleChoiceQuiz={quiz}></PlayQuizCard>
                </div>)}
        </div>
    )
}