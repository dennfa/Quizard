import {MultipleChoiceQuiz} from "../Models/MultipleChoiceQuiz.tsx";

export type props = {
    readonly multipleChoiceQuiz:MultipleChoiceQuiz,
}

export default function QuizCard(props:props){
    return(
        <div className="QuizCard">
            <h3>{props.multipleChoiceQuiz.name}</h3>
            <p>Number of Questions: {props.multipleChoiceQuiz.numberOfQuestions}</p>
            <p>Author: {props.multipleChoiceQuiz.author}</p>
        </div>
    )
}