import {MultipleChoiceQuestion} from "./MultipleChoiceQuestion.tsx";

export type MultipleChoiceQuiz = {
    id?:string,
    name:string,
    numberOfQuestions:number,
    multipleChoiceQuestions:MultipleChoiceQuestion[],
}