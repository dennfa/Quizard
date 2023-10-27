import {MultipleChoiceQuestion} from "./MultipleChoiceQuestion.tsx";

export type MultipleChoiceQuiz = {
    name:string,
    description:string,
    numberOfQuestions:number,
    multipleChoiceQuestions:MultipleChoiceQuestion[],
}