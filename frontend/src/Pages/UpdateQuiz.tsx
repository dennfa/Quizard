import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {MultipleChoiceQuiz} from "../Models/MultipleChoiceQuiz.tsx";
import {MultipleChoiceQuestion} from "../Models/MultipleChoiceQuestion.tsx";
import AddMultipleChoiceQuestion from "../Components/AddMultipleChoiceQuestion.tsx";
import DeleteIcon from "../Assets/trash.svg";

export default function UpdateQuiz() {

    const {id} = useParams();
    const [multipleChoiceQuiz, setMultipleChoiceQuiz] = useState<MultipleChoiceQuiz>({
        name: "", numberOfQuestions: 0,
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
            falseAnswers: ["", "", ""],
            trueAnswer: "",
        }
        setMultipleChoiceQuiz({
            ...multipleChoiceQuiz, multipleChoiceQuestions: [
                ...multipleChoiceQuiz.multipleChoiceQuestions, newQuestion]
        });
    }

    function handleDeleteQuestion(index: number) {
        const updatedQuestions = multipleChoiceQuiz.multipleChoiceQuestions.filter((_, i) => i !== index)
        setMultipleChoiceQuiz({
            ...multipleChoiceQuiz, multipleChoiceQuestions: updatedQuestions
        })
    }

    function myCallBackFunction(userInput: string, multipleChoiceProperty: string, index: number) {
        const updatedQuestions = multipleChoiceQuiz.multipleChoiceQuestions.slice()
        switch (multipleChoiceProperty) {
            case "question":
                updatedQuestions[index].question = userInput
                break
            case "falseAnswer1":
                updatedQuestions[index].falseAnswers[0] = userInput
                break
            case "falseAnswer2":
                updatedQuestions[index].falseAnswers[1] = userInput
                break
            case "falseAnswer3":
                updatedQuestions[index].falseAnswers[2] = userInput
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
            .then(() => navigate("/update"))
            .catch(error => {
                console.error("Error during delete:", error)
            })
    }

    return (
        <div className="PageContainer">
            <h2 className="PageHeader">Edit Quiz</h2>
            <button className="QuitButton" type="button" onClick={() => navigate("/")}>X</button>
            <div className="CreateQuizName">
                <label htmlFor="quizName">Quiz Name:</label>
                <input
                    type="text"
                    id="quizName"
                    name="quizName"
                    value={multipleChoiceQuiz.name}
                    onChange={event => setMultipleChoiceQuiz({
                        ...multipleChoiceQuiz, name: event.target.value
                    })}
                />
            </div>
            <p className="CreateQuizNumberOfQuestions">Current number of
                questions: {multipleChoiceQuiz.multipleChoiceQuestions.length}</p>
            {multipleChoiceQuiz.multipleChoiceQuestions.map((question: MultipleChoiceQuestion, index: number) =>
                <div className="CreateQuizQuestion" key={index}>
                    <img className="DeleteQuestionButton" src={DeleteIcon} alt="Delete Icon"
                         onClick={() => handleDeleteQuestion(index)}/>
                    <AddMultipleChoiceQuestion
                        key={question + index.toString()}
                        index={index}
                        multipleChoiceQuestion={question}
                        myCallBack={myCallBackFunction}
                    />
                </div>)}
            <button className="AddQuestionButton" type="button" onClick={handleAddQuestion}>+</button>
            <div className="SaveDeleteButtonContainer">
                <button className="SaveDeleteQuizButton" type="button" onClick={deleteQuiz}>Delete Quiz</button>
                <button className="SaveDeleteQuizButton" type="button" onClick={saveQuiz}>Save Quiz</button>
            </div>
        </div>
    )
}