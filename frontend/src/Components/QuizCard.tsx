import {MultipleChoiceQuiz} from "../Models/MultipleChoiceQuiz.tsx";

export type props = {
    multipleChoiceQuiz:MultipleChoiceQuiz,
}

export default function QuizCard(props:props){
    return(
        <div className="QuizCard">
            <h2>{props.multipleChoiceQuiz.name}</h2>
            <p>Description: {props.multipleChoiceQuiz.description}</p>
            <p>Number of Questions: {props.multipleChoiceQuiz.numberOfQuestions}</p>
        </div>
    )
}