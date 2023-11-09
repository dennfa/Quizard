import "../../Styling/QuizCard.css"
import {PlayMultipleChoiceQuiz} from "../../Models/play/PlayMultipleChoiceQuiz.tsx";

export type props = {
    playMultipleChoiceQuiz:PlayMultipleChoiceQuiz,
}

export default function PlayQuizCard(props:props){
    return(
        <div className="QuizCard">
            <h2>{props.playMultipleChoiceQuiz.name}</h2>
            <p>Number of Questions: {props.playMultipleChoiceQuiz.numberOfQuestions}</p>
        </div>
    )
}