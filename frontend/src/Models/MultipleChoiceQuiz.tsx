import {MultipleChoiceQuestion} from "./MultipleChoiceQuestion.tsx";

export type MultipleChoiceQuiz = {
    id?:string,
    name:string,
    description:string,
    numberOfQuestions:number,
    multipleChoiceQuestions:MultipleChoiceQuestion[],
}