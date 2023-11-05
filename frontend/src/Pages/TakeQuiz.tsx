import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import {PlayMultipleChoiceQuiz} from "../Models/play/PlayMultipleChoiceQuiz.tsx";

export default function TakeQuiz() {

    const {id} = useParams();
    const [playMultipleChoiceQuiz, setPlayMultipleChoiceQuiz] = useState<PlayMultipleChoiceQuiz>({
        id: "", name: "", description: "", numberOfQuestions: 0,
        playMultipleChoiceQuestions: [],
    })
    const navigate = useNavigate()
    const [index, setIndex] = useState(0)
    const [disableButtons, setDisableButtons] = useState(false)
    const [correctAnswers, setCorrectAnswers] = useState(0)

    function toNextQuestion(answer: string, buttonIndex: number) {
        setDisableButtons(true);

        axios.post("/api/take/" + playMultipleChoiceQuiz.id, index.toString())
            .then(response => {
                if (answer !== response.data) {
                    console.log("wrong")
                    const buttons = document.querySelectorAll("button");
                    buttons[buttonIndex].classList.add("wrong-answer");
                } else {
                    console.log("correct")
                    setCorrectAnswers(correctAnswers + 1)
                }
                console.log(response.data)
                const correctAnswerButton = document.querySelector(`button[value="${response.data}"]`)

                if (correctAnswerButton) correctAnswerButton.classList.add("correct-answer");

                setTimeout(() => {
                    setIndex(index + 1);
                    setDisableButtons(false);
                }, 1000);
            })
            .catch(error => {
                console.error("Error during answer check: ", error)
                setDisableButtons(false);
            })
    }

    useEffect(() => {
        axios.get("/api/take/" + id)
            .then(response => {
                setPlayMultipleChoiceQuiz(response.data)
            })
            .catch(error => {
                console.error("Error during quiz loading: ", error)
            })
    }, [id])

    const isIndexValid = index < playMultipleChoiceQuiz.playMultipleChoiceQuestions.length;

    return (
        <div>
            {isIndexValid ? (
                <div>
                    <h3>{playMultipleChoiceQuiz.playMultipleChoiceQuestions[index].question}</h3>
                    {playMultipleChoiceQuiz.playMultipleChoiceQuestions[index].answers.map((answer, buttonIndex) =>
                        <button
                            type="button"
                            key={answer + buttonIndex}
                            onClick={() => toNextQuestion(answer, buttonIndex)}
                            disabled={disableButtons}
                            value={answer}>
                            {answer}
                        </button>
                    )}
                </div>
            ) : (
                <div>
                    <p>Quiz is complete</p>
                    <p>Score: {correctAnswers}/{playMultipleChoiceQuiz.numberOfQuestions}</p>
                </div>
            )}

            <button type="button" onClick={() => navigate("/")}>Quit Quiz</button>
        </div>
    )
}