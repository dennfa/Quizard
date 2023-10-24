import {MultipleChoiceQuestion} from "../Models/MultipleChoiceQuestion.tsx";
import {useEffect, useState} from "react";

export type props = {
    multipleChoiceQuestion: MultipleChoiceQuestion,
    index: number,
    myCallBack: (questionData: MultipleChoiceQuestion, index: number) => void,
}

export default function AddMultipleChoiceQuestion(props: props) {

    const [question, setQuestion] = useState<string>("")
    const [falseAnswer, setFalseAnswer] = useState<string>("")
    const [trueAnswer, setTrueAnswer] = useState<string>("")

    useEffect(() => {
        props.myCallBack({question, falseAnswer, trueAnswer}, props.index)
    }, [question, falseAnswer, trueAnswer])


    return (
        <div>
            <label htmlFor="question">Question:</label>
            <input
                id="question"
                name="question"
                type="text"
                value={props.multipleChoiceQuestion.question}
                onChange={event => setQuestion(event.target.value)}
            />
            <label htmlFor="falseAnswer">False Answer:</label>
            <input
                id="falseAnswer"
                name="falseAnswer"
                type="text"
                value={props.multipleChoiceQuestion.falseAnswer}
                onChange={event => setFalseAnswer(event.target.value)}
            />
            <label htmlFor="trueAnswer">True Answer:</label>
            <input
                id="trueAnswer"
                name="trueAnswer"
                type="text"
                value={props.multipleChoiceQuestion.trueAnswer}
                onChange={event => setTrueAnswer(event.target.value)}
            />
        </div>
    )
}