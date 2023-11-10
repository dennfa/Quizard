import {useState} from "react";
import {MultipleChoiceQuestion} from "../Models/MultipleChoiceQuestion.tsx";
import AddMultipleChoiceQuestion from "../Components/AddMultipleChoiceQuestion.tsx";
import {MultipleChoiceQuiz} from "../Models/MultipleChoiceQuiz.tsx";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import DeleteIcon from "../Assets/trash.svg"

export default function CreateQuiz() {

    const [questions, setQuestions] = useState<MultipleChoiceQuestion[]>([])
    const [quizName, setQuizName] = useState<string>("")
    const navigate = useNavigate()

    function handleAddQuestion() {
        const newQuestion: MultipleChoiceQuestion = {
            question: "",
            falseAnswers: ["","",""],
            trueAnswer: "",
        }
        setQuestions([...questions, newQuestion]);
    }

    function handleDeleteQuestion(index: number) {
        const updatedQuestions = questions.filter((_, i) => i !== index)
        setQuestions(updatedQuestions)
    }

    function myCallBackFunction(userInput: string, multipleChoiceProperty: string, index: number) {
        const updatedQuestions = questions.slice()
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
        setQuestions(updatedQuestions)
    }

    function saveQuiz() {
        const quizData: MultipleChoiceQuiz = {
            name: quizName,
            numberOfQuestions: questions.length,
            multipleChoiceQuestions: questions,
        }
        axios.post("/api/create", quizData)
            .then(() => navigate("/"))
            .catch(error => {
                console.error("Error while saving: ", error)
            })
    }

    return (
        <div className="PageContainer">
            <h2 className="PageHeader">Create Quiz</h2>
                <button className = "QuitButton" type="button" onClick={() => navigate("/")}>X</button>
                <div className="CreateQuizName">
                    <label htmlFor="quizName">Quiz Name:</label>
                    <input
                        type="text"
                        id="quizName"
                        name="quizName"
                        value={quizName}
                        onChange={event => setQuizName(event.target.value)}
                    />
                </div>
                <p className="CreateQuizNumberOfQuestions">Current number of questions: {questions.length}</p>
                {questions.map((question: MultipleChoiceQuestion, index: number) =>
                    <div className="CreateQuizQuestion" key={index}>
                        <img className="DeleteQuestionButton" src={DeleteIcon} alt="Delete Icon" onClick={() => handleDeleteQuestion(index)}/>
                        <AddMultipleChoiceQuestion
                            key={question + index.toString()}
                            index={index}
                            multipleChoiceQuestion={question}
                            myCallBack={myCallBackFunction}
                        />
                    </div>
                )}
                <button className="AddQuestionButton" type="button" onClick={handleAddQuestion}>+</button>
                <button className="SaveDeleteQuizButton" type="button" onClick={saveQuiz}>Save Quiz</button>
        </div>
    )
}