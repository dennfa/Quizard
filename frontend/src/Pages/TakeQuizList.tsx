import {useEffect, useState} from "react";
import {MultipleChoiceQuiz} from "../Models/MultipleChoiceQuiz.tsx";
import axios from "axios";
import QuizCard from "../Components/QuizCard.tsx";
import {useNavigate} from "react-router-dom";

export default function TakeQuizList() {

    const [multipleChoiceQuizzes, setMultipleChoiceQuizzes] = useState<MultipleChoiceQuiz[]>([])
    const navigate = useNavigate()

    useEffect(()=>
    {
        axios.get("/api")
            .then(response => {
                setMultipleChoiceQuizzes(response.data)
            })
            .catch(error => {
                console.error("Error during quiz loading: ", error)
            })
    },[])

    return (
        <div className="TakeQuizList">
            <button type="button" onClick={()=>navigate("/")}>Back</button>
            {multipleChoiceQuizzes.map((quiz)=>
                <QuizCard key={quiz.id} multipleChoiceQuiz={quiz}></QuizCard>)}
        </div>
    )
}