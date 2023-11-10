import {MultipleChoiceQuestion} from "../Models/MultipleChoiceQuestion.tsx";

export type props = {
    readonly multipleChoiceQuestion: MultipleChoiceQuestion,
    readonly index: number,
    readonly myCallBack: (userInput: string, multipleChoiceProperty: string, index: number) => void,
}

export default function AddMultipleChoiceQuestion(props: props) {


    return (
        <div className="AddMultipleChoiceQuestion">
            <div className="AddMultipleChoiceQuestionElement">
                <label htmlFor="question">Question:</label>
                <input
                    id="question"
                    name="question"
                    type="text"
                    value={props.multipleChoiceQuestion.question}
                    onChange={event => props.myCallBack(event.target.value, "question", props.index)}
                />
            </div>
            <div className="AddMultipleChoiceQuestionElement">
                <label htmlFor="falseAnswer1">False Answer 1:</label>
                <input
                    id="falseAnswer1"
                    name="falseAnswer1"
                    type="text"
                    value={props.multipleChoiceQuestion.falseAnswers[0]}
                    onChange={event => props.myCallBack(event.target.value, "falseAnswer1", props.index)}
                />
            </div>
            <div className="AddMultipleChoiceQuestionElement">
                <label htmlFor="falseAnswer2">False Answer 2:</label>
                <input
                    id="falseAnswer2"
                    name="falseAnswer2"
                    type="text"
                    value={props.multipleChoiceQuestion.falseAnswers[1]}
                    onChange={event => props.myCallBack(event.target.value, "falseAnswer2", props.index)}
                />
            </div>
            <div className="AddMultipleChoiceQuestionElement">
                <label htmlFor="falseAnswer3">False Answer 3:</label>
                <input
                    id="falseAnswer3"
                    name="falseAnswer3"
                    type="text"
                    value={props.multipleChoiceQuestion.falseAnswers[2]}
                    onChange={event => props.myCallBack(event.target.value, "falseAnswer3", props.index)}
                />
            </div>
            <div className="AddMultipleChoiceQuestionElement">
                <label htmlFor="trueAnswer">True Answer:</label>
                <input
                    id="trueAnswer"
                    name="trueAnswer"
                    type="text"
                    value={props.multipleChoiceQuestion.trueAnswer}
                    onChange={event => props.myCallBack(event.target.value, "trueAnswer", props.index)}
                />
            </div>
        </div>
    )
}