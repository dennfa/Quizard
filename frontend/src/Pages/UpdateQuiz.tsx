import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {MultipleChoiceQuiz} from "../Models/MultipleChoiceQuiz.tsx";
import {MultipleChoiceQuestion} from "../Models/MultipleChoiceQuestion.tsx";
import AddMultipleChoiceQuestion from "../Components/AddMultipleChoiceQuestion.tsx";

export default function UpdateQuiz() {

    const {id} = useParams();
    const [multipleChoiceQuiz, setMultipleChoiceQuiz] = useState<MultipleChoiceQuiz>({
        name: "", description: "", numberOfQuestions: 0,
        multipleChoiceQuestions: [],
    })
    const navigate = useNavigate()

    useEffect(() => {
        axios.get("/api/update/" + id)
            .then(response => {
                setMultipleChoiceQuiz(response.data)
                console.log(response.data)
            })
            .catch(error => {
                console.error("Error during quiz loading: ", error)
            })
    }, [id])

    function handleAddQuestion() {
        const newQuestion: MultipleChoiceQuestion = {
            question: "",
            falseAnswer: "",
            trueAnswer: "",
        }
        setMultipleChoiceQuiz({
            ...multipleChoiceQuiz, multipleChoiceQuestions: [
                ...multipleChoiceQuiz.multipleChoiceQuestions, newQuestion]
        });
    }

    function myCallBackFunction(userInput: string, multipleChoiceProperty: string, index: number) {
        const updatedQuestions = multipleChoiceQuiz.multipleChoiceQuestions.slice()
        switch (multipleChoiceProperty) {
            case "question":
                updatedQuestions[index].question = userInput
                break
            case "falseAnswer":
                updatedQuestions[index].falseAnswer = userInput
                break
            case "trueAnswer":
                updatedQuestions[index].trueAnswer = userInput
                break
        }
        setMultipleChoiceQuiz({...multipleChoiceQuiz, multipleChoiceQuestions: updatedQuestions})
    }

    function saveQuiz() {
        const quizData: MultipleChoiceQuiz = {
            id: multipleChoiceQuiz.id,
            name: multipleChoiceQuiz.name,
            description: multipleChoiceQuiz.description,
            numberOfQuestions: multipleChoiceQuiz.multipleChoiceQuestions.length,
            multipleChoiceQuestions: multipleChoiceQuiz.multipleChoiceQuestions,
        }
        axios.put("/api/update", quizData)
            .then(() => navigate("/update")
            )
            .catch(error => {
                console.error("Error while saving: ", error)
            })
    }

    function deleteQuiz() {
        axios.delete("/api/update/" + id)
            .then(() =>navigate("/update"))
            .catch(error => {
                console.error("Error during delete:", error)
            })
    }

    return (
        <div>
            <form>
                <div className="QuizName">
                    <label htmlFor="quizName">Quiz Name:</label>
                    <input
                        type="text"
                        id="quizName"
                        name="quizName"
                        placeholder="Enter the name of your quiz here"
                        value={multipleChoiceQuiz.name}
                        onChange={event => setMultipleChoiceQuiz({
                            ...multipleChoiceQuiz, name: event.target.value
                        })}
                    />
                </div>
                <div className="QuizDescription">
                    <label htmlFor="quizDescription">Quiz Description:</label>
                    <input
                        type="text"
                        id="quizDescription"
                        name="quizDescription"
                        placeholder="Describe your quiz here"
                        value={multipleChoiceQuiz.description}
                        onChange={event => setMultipleChoiceQuiz({
                            ...multipleChoiceQuiz, description: event.target.value
                        })}
                    />
                </div>
                <p>Current number of questions: {multipleChoiceQuiz.multipleChoiceQuestions.length}</p>
                {multipleChoiceQuiz.multipleChoiceQuestions.map((question: MultipleChoiceQuestion, index: number) =>
                    <AddMultipleChoiceQuestion
                        key={index}
                        index={index}
                        multipleChoiceQuestion={question}
                        myCallBack={myCallBackFunction}
                    />)}
                <button type="button" onClick={handleAddQuestion}>Add Question</button>
                <button type="button" onClick={saveQuiz}>Save Quiz</button>
                <button type="button" onClick={deleteQuiz}>Delete Quiz</button>
            </form>
        </div>
    )
}