
export default function AddMultipleChoiceQuestion(){

    return(
        <div>
            <label htmlFor="question">Question:</label>
        <input
            id="question"
            name="question"
            type="text"
            />
            <label htmlFor="falseAnswer">False Answer:</label>
            <input
                id="falseAnswer"
                name="falseAnswer"
                type="text"
            />
            <label htmlFor="trueAnswer">True Answer:</label>
            <input
                id="trueAnswer"
                name="trueAnswer"
                type="text"
            />
        </div>
    )
}