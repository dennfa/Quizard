import {useEffect, useState} from "react";
import {MultipleChoiceQuiz} from "../Models/MultipleChoiceQuiz.tsx";
import axios from "axios";
import QuizCard from "../Components/QuizCard.tsx";

export default function TakeQuiz() {

    const [multipleChoiceQuizzes, setMultipleChoiceQuizzes] = useState<MultipleChoiceQuiz[]>([])

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
        <div className="TakeQuiz">
            {multipleChoiceQuizzes.map((quiz)=>
                <QuizCard key={quiz.id} multipleChoiceQuiz={quiz}></QuizCard>)}
        </div>
    )
}