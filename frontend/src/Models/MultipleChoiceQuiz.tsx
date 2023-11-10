import {MultipleChoiceQuestion} from "./MultipleChoiceQuestion.tsx";

export type MultipleChoiceQuiz = {
    id?:string,
    author?:string,
    name:string,
    numberOfQuestions:number,
    multipleChoiceQuestions:MultipleChoiceQuestion[],
}