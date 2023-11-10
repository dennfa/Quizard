import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import {PlayMultipleChoiceQuiz} from "../Models/play/PlayMultipleChoiceQuiz.tsx";

export default function TakeQuiz() {

    const {id} = useParams();
    const [playMultipleChoiceQuiz, setPlayMultipleChoiceQuiz] = useState<PlayMultipleChoiceQuiz>({
        id: "", author: "", name: "", numberOfQuestions: 0,
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
                    const buttons = document.querySelectorAll("button");
                    buttons[buttonIndex].classList.add("wrong-answer");
                } else {
                    setCorrectAnswers(correctAnswers + 1)
                }
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
                <div className="PageContainer">
                    <h4 className="TakeQuizQuestionCounter">Question {index + 1}/{playMultipleChoiceQuiz.numberOfQuestions}</h4>
                    <h4 className="TakeQuizQuestion">{playMultipleChoiceQuiz.playMultipleChoiceQuestions[index].question}</h4>
                    <div className="TakeQuizAnswersContainer">
                        {playMultipleChoiceQuiz.playMultipleChoiceQuestions[index].answers.map((answer, buttonIndex) =>
                            <button
                                className="TakeQuizAnswerButton"
                                type="button"
                                key={answer}
                                onClick={() => toNextQuestion(answer, buttonIndex)}
                                disabled={disableButtons}
                                value={answer}>
                                {answer}
                            </button>
                        )}
                    </div>
                </div>
            ) : (
                <div className="QuizCompleteScreen">
                    <p>Quiz complete!</p>
                    <p>Score: {correctAnswers}/{playMultipleChoiceQuiz.numberOfQuestions}</p>
                </div>
            )}
            <button
                className="QuitButton"
                type="button"
                onClick={() => navigate("/")}>X
            </button>
        </div>
    )
}