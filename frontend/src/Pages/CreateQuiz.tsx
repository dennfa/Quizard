import {useState} from "react";
import {MultipleChoiceQuestion} from "../Models/MultipleChoiceQuestion.tsx";
import AddMultipleChoiceQuestion from "../Components/AddMultipleChoiceQuestion.tsx";

export default function CreateQuiz() {

    const [questions, setQuestions] = useState<MultipleChoiceQuestion[]>([])

    function handleAddQuestion() {
        const newQuestion: MultipleChoiceQuestion = {
            question: "",
            falseAnswer: "",
            trueAnswer: "",
        }
        setQuestions([...questions, newQuestion]);
    }

    function myCallBackFunction(userInput: string, multipleChoiceProperty: string, index:number){
        const questions2 = questions.slice()
        switch(multipleChoiceProperty){
            case "question": questions2[index].question = userInput
                break
            case "falseAnswer": questions2[index].falseAnswer = userInput
                break
            case "trueAnswer": questions2[index].trueAnswer = userInput
                break
        }

        setQuestions(questions2)
    }

    function saveQuiz() {
        console.log("Hello")
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
                <button onClick={saveQuiz}>Save Quiz</button>
            </form>
        </div>
    )
}