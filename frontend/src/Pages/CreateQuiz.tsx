import {JSX, useState} from "react";
import AddMultipleChoiceQuestion from "../Components/AddMultipleChoiceQuestion.tsx";

export default function CreateQuiz() {

    const [questions, setQuestions] = useState<JSX.Element[]>([])

    function handleAddQuestion() {
        const newQuestion = <AddMultipleChoiceQuestion key={questions.length}/>;
        setQuestions([...questions, newQuestion]);
    }

    function saveQuiz(){
        console.log("Hello")
 }

    return (
        <div className="CreateQuiz">
            <div className="QuizName">
                <label htmlFor="quizName">Quiz Name:</label>
                <input
                    type="text"
                    id="quizName"
                    name="quizName"
                    placeholder="Enter the name of your quiz here"
                />
            </div>
            <p>Current number of questions: {questions.length}</p>
            {questions}
            <button onClick={handleAddQuestion}>Add Question</button>
            <button onClick={saveQuiz}>Save Quiz</button>
        </div>
    )
}