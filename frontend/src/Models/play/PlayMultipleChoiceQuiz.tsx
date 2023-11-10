import {PlayMultipleChoiceQuestion} from "./PlayMultipleChoiceQuestion.tsx";

export type PlayMultipleChoiceQuiz = {
    id:string,
    author:string,
    name:string,
    numberOfQuestions:number,
    playMultipleChoiceQuestions:PlayMultipleChoiceQuestion[],
}