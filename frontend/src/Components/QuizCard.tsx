import {MultipleChoiceQuiz} from "../Models/MultipleChoiceQuiz.tsx";

export type props = {
    multipleChoiceQuiz:MultipleChoiceQuiz,
}

export default function QuizCard(props:props){
    return(
        <div>
            <h2>{props.multipleChoiceQuiz.name}</h2>
            <p>{props.multipleChoiceQuiz.description}</p>
            <p>{props.multipleChoiceQuiz.numberOfQuestions}</p>
        </div>
    )
}