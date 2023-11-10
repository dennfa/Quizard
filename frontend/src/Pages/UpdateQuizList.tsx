import {useEffect, useState} from "react";
import {MultipleChoiceQuiz} from "../Models/MultipleChoiceQuiz.tsx";
import axios from "axios";
import QuizCard from "../Components/QuizCard.tsx";
import {useNavigate} from "react-router-dom";
import BackIcon from "../Assets/back.svg";

export default function UpdateQuizList() {

    const [multipleChoiceQuizzes, setMultipleChoiceQuizzes] = useState<MultipleChoiceQuiz[]>([])
    const navigate = useNavigate();

    useEffect(()=>
    {
        axios.get("/api/update")
            .then(response => {
                setMultipleChoiceQuizzes(response.data)
            })
            .catch(error => {
                console.error("Error during quiz loading: ", error)
            })
    },[])

    return (
        <div className="PageContainer">
            <h2 className="PageHeader">Edit Quiz</h2>
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
            {multipleChoiceQuizzes.map(quiz=>
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
                <QuizCard key={quiz.id} multipleChoiceQuiz={quiz}></QuizCard>
                </div>)}
        </div>
    )
}