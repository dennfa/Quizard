import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {PlayMultipleChoiceQuiz} from "../Models/play/PlayMultipleChoiceQuiz.tsx";
import PlayQuizCard from "../Components/play/PlayQuizCard.tsx";

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
        <div className="TakeQuizList">
            <button type="button" onClick={()=>navigate("/")}>Back</button>
            {playMultipleChoiceQuizzes.map(quiz=>
                <button  key = {quiz.id} onClick={()=>navigate("" + quiz.id)}>
                    <PlayQuizCard key={quiz.id} playMultipleChoiceQuiz={quiz}></PlayQuizCard>
                </button>)}
        </div>
    )
}