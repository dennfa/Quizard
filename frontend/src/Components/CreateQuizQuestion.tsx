import {MultipleChoiceQuestion} from "../Models/MultipleChoiceQuestion.tsx";
import DeleteIcon from "../Assets/trash.svg";
import AddMultipleChoiceQuestion from "./AddMultipleChoiceQuestion.tsx";

export type props = {
    readonly question:MultipleChoiceQuestion,
    readonly index:number,
    readonly handleDeleteQuestion:(index:number)=>void,
    readonly addMultipleChoiceQuestionCallBack:(userInput: string, multipleChoiceProperty: string, index: number) => void,
}

export default function CreateQuizQuestion(props:props) {
    return(
        <div className="CreateQuizQuestion" key={props.question + props.index.toString()}>
            <img
                className="DeleteQuestionButton"
                src={DeleteIcon}
                alt="Delete Icon"
                onClick={() => props.handleDeleteQuestion(props.index)}
                onKeyDown={(event) => {
                    if (event.key === 'Enter') {
                        props.handleDeleteQuestion(props.index);
                    }
                }}
                tabIndex={0}
            />
            <AddMultipleChoiceQuestion
                key={props.question + props.index.toString()}
                index={props.index}
                multipleChoiceQuestion={props.question}
                addMultipleChoiceQuestionCallBack={props.addMultipleChoiceQuestionCallBack}
            />
        </div>
    )
}