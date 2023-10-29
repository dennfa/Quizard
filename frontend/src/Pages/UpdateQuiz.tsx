import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {MultipleChoiceQuiz} from "../Models/MultipleChoiceQuiz.tsx";
import QuizCard from "../Components/QuizCard.tsx";

export default function UpdateQuiz(){

        const {id} = useParams();
        const [multipleChoiceQuiz, setMultipleChoiceQuiz] = useState<MultipleChoiceQuiz>({} as MultipleChoiceQuiz)

    useEffect(()=>
    {
        axios.get("/api/update/" + id)
            .then(response => {
                setMultipleChoiceQuiz(response.data)
            })
            .catch(error => {
                console.error("Error during quiz loading: ", error)
            })
    },[id])

    return (
        <div>
                <QuizCard multipleChoiceQuiz={multipleChoiceQuiz}></QuizCard>
        </div>
    )
}