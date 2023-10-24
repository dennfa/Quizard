import {MultipleChoiceQuestion} from "./MultipleChoiceQuestion.tsx";

export type MultipleChoiceQuiz = {
    quizName:string,
    multipleChoiceQuestions:MultipleChoiceQuestion[],
}