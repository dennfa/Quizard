import {PlayMultipleChoiceQuiz} from "../../Models/play/PlayMultipleChoiceQuiz.tsx";

export type props = {
    playMultipleChoiceQuiz:PlayMultipleChoiceQuiz,
}

export default function PlayQuizCard(props:props){
    return(
        <div className="QuizCard">
            <h3>{props.playMultipleChoiceQuiz.name}</h3>
            <p>Number of Questions: {props.playMultipleChoiceQuiz.numberOfQuestions}</p>
        </div>
    )
}