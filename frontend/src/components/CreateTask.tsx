import {type FormEvent, useState} from "react";
import {createTask} from "../service/api.ts";
import type {CreateTaskRequest} from "../types/taskTypes.ts";

type CreateTaskProps = {
    onTaskCreate: (task: CreateTaskRequest) => void
}

export default function CreateTask({onTaskCreate}: CreateTaskProps) {
    const [addTask, setAddTask] = useState<boolean>(false);
    const [taskValue, setTaskValue] = useState<CreateTaskRequest>({
        id: "", title: "", note: "", status: "OPEN"
    })

    const handleDoubleClick = () => setAddTask(true);

    const handleChange = (field: keyof CreateTaskRequest, value: string) => {
        setTaskValue((prevState) => ({
            ...prevState,
            [field]: value
        }));
    }

    const onFormSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        onTaskCreate(taskValue);
        createTask(taskValue)
            .catch((e) => console.log("Error while creating data.", e));
        setTaskValue({id:"", title:"", note:"", status:"OPEN"})
        setAddTask(false)
    }

    return (
        <>
            <div onDoubleClick={handleDoubleClick} className="add-task-form">
                {!addTask ? (
                        <p>Add a Task</p>) :
                    <form onSubmit={onFormSubmit}>
                        <input type="text" value={taskValue.title}
                               onChange={(e) =>
                                   handleChange("title" , e.target.value)}
                               placeholder="Enter task..."
                               autoFocus
                        />
                        <input type="text" value={taskValue.note}
                               onChange={(e) =>
                                   handleChange("note" , e.target.value)}
                               placeholder="Enter note..."
                        />
                        <input type="submit" value="Add Task"/>
                        <button type="button" value="Cancel"
                                onClick={() => onTaskCreate(taskValue)}>Cancel</button>
                    </form>
                }
            </div>
        </>
    )
}


