import {MultipleChoiceQuestion} from "../Models/MultipleChoiceQuestion.tsx";

export function multipleChoicePropertyAction(
    userInput: string,
    multipleChoiceProperty: string,
    index: number,
    updatedQuestions:MultipleChoiceQuestion[]):void {
    switch (multipleChoiceProperty) {
        case "question":
            updatedQuestions[index].question = userInput
            break
        case "falseAnswer1":
            updatedQuestions[index].falseAnswers[0] = userInput
            break
        case "falseAnswer2":
            updatedQuestions[index].falseAnswers[1] = userInput
            break
        case "falseAnswer3":
            updatedQuestions[index].falseAnswers[2] = userInput
            break
        case "trueAnswer":
            updatedQuestions[index].trueAnswer = userInput
            break
    }
}