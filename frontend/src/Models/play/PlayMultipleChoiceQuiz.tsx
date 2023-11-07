import {PlayMultipleChoiceQuestion} from "./PlayMultipleChoiceQuestion.tsx";

export type PlayMultipleChoiceQuiz = {
    id:string,
    name:string,
    numberOfQuestions:number,
    playMultipleChoiceQuestions:PlayMultipleChoiceQuestion[],
}