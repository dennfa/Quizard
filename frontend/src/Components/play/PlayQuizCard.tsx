import {PlayMultipleChoiceQuiz} from "../../Models/play/PlayMultipleChoiceQuiz.tsx";

export type props = {
    readonly playMultipleChoiceQuiz:PlayMultipleChoiceQuiz,
}

export default function PlayQuizCard(props:props){
    return(
        <div className="QuizCard">
            <h3>{props.playMultipleChoiceQuiz.name}</h3>
            <p>Number of Questions: {props.playMultipleChoiceQuiz.numberOfQuestions}</p>
            <p>Author: {props.playMultipleChoiceQuiz.author}</p>
        </div>
    )
}