import {MultipleChoiceQuestion} from "../Models/MultipleChoiceQuestion.tsx";

export type props = {
    multipleChoiceQuestion: MultipleChoiceQuestion,
    index: number,
    myCallBack: (userInput: string, multipleChoiceProperty: string, index: number) => void,
}

export default function AddMultipleChoiceQuestion(props: props) {


    return (
        <div>
            <label htmlFor="question">Question:</label>
            <input
                id="question"
                name="question"
                type="text"
                value={props.multipleChoiceQuestion.question}
                onChange={event => props.myCallBack(event.target.value,"question",props.index)}
            />
            <label htmlFor="falseAnswer">False Answer:</label>
            <input
                id="falseAnswer"
                name="falseAnswer"
                type="text"
                value={props.multipleChoiceQuestion.falseAnswer}
                onChange={event => props.myCallBack(event.target.value,"falseAnswer",props.index)}
            />
            <label htmlFor="trueAnswer">True Answer:</label>
            <input
                id="trueAnswer"
                name="trueAnswer"
                type="text"
                value={props.multipleChoiceQuestion.trueAnswer}
                onChange={event => props.myCallBack(event.target.value,"trueAnswer",props.index)}
            />
        </div>
    )
}