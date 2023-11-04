import {PlayMultipleChoiceQuestion} from "./PlayMultipleChoiceQuestion.tsx";

export type PlayMultipleChoiceQuiz = {
    id:string,
    name:string,
    description:string,
    numberOfQuestions:number,
    playMultipleChoiceQuestions:PlayMultipleChoiceQuestion[],
}