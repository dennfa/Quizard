import {useState} from "react";
import {MultipleChoiceQuestion} from "../Models/MultipleChoiceQuestion.tsx";
import AddMultipleChoiceQuestion from "../Components/AddMultipleChoiceQuestion.tsx";
import {MultipleChoiceQuiz} from "../Models/MultipleChoiceQuiz.tsx";
import axios from "axios";

export default function CreateQuiz() {

    const [questions, setQuestions] = useState<MultipleChoiceQuestion[]>([])
    const [quizName, setQuizName] = useState<string>("")

    function handleAddQuestion() {
        const newQuestion: MultipleChoiceQuestion = {
            question: "",
            falseAnswer: "",
            trueAnswer: "",
        }
        setQuestions([...questions, newQuestion]);
    }

    function myCallBackFunction(userInput: string, multipleChoiceProperty: string, index: number) {
        const updatedQuestions = questions.slice()
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
        setQuestions(updatedQuestions)
    }

    function saveQuiz() {
        const quizData: MultipleChoiceQuiz = {
            quizName: quizName,
            multipleChoiceQuestions: questions,
        }
        axios.post("/api/create", quizData)
            .then(response => {
                console.log("Successfully saved: ", response.data)
            })
            .catch(error => {
                console.error("Error while saving: ", error)
            })
    }

    return (
        <div className="CreateQuiz">
            <form>
                <div className="QuizName">
                    <label htmlFor="quizName">Quiz Name:</label>
                    <input
                        type="text"
                        id="quizName"
                        name="quizName"
                        placeholder="Enter the name of your quiz here"
                        value={quizName}
                        onChange={event => setQuizName(event.target.value)}
                    />
                </div>
                <p>Current number of questions: {questions.length}</p>
                {questions.map((question: MultipleChoiceQuestion, index: number) => <AddMultipleChoiceQuestion
                    key={index}
                    index={index}
                    multipleChoiceQuestion={question}
                    myCallBack={myCallBackFunction}
                />)}
                <button type="button" onClick={handleAddQuestion}>Add Question</button>
                <button type="button" onClick={saveQuiz}>Save Quiz</button>
            </form>
        </div>
    )
}