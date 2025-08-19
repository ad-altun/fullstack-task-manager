import {type ChangeEvent, type FormEvent, useState} from "react";
import type { TaskApiResponse } from "../types/taskTypes.ts";
import {updateTask} from "../service/api.ts";

type EditTaskProps = {
    onTaskEdit: (task: TaskApiResponse) => void,
    isEditActive: boolean,
    task: TaskApiResponse
}

export default function EditTask({onTaskEdit, isEditActive, task}: EditTaskProps) {
    const [updatedTask, setUpdatedTask] = useState<TaskApiResponse>({
        id: task.id,
        title: task.title,
        note: task.note,
        status: task.status
    })

    // useEffect(() => {
    //     setUpdatedTask({
    //         id: task.id,
    //         title: task.title,
    //         note: task.note,
    //         status: task.status
    //     })
    // }, [task]);


    const handleChange = (field: keyof TaskApiResponse, value: string) => {
        setUpdatedTask(prev => ({
            ...prev,
            [field]: value
        }))
    }


    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (!updatedTask.title.trim()   ||
            !updatedTask.status.trim()  ||
            !updatedTask.note.trim()) {
            console.log("Input can not be empty")
            return;
        }

        try {
            const serverUpdatedTask: TaskApiResponse = await updateTask(updatedTask)
            onTaskEdit(serverUpdatedTask);
        } catch (e) {
            console.log("Error while editing task:", e)
        }
    }

    if (!isEditActive) return null;

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input type='text'
                       value={updatedTask.title}
                       onChange={(e: ChangeEvent<HTMLInputElement>) =>
                           handleChange("title", e.target.value)}
                       autoFocus/>
                <input type='text'
                       value={updatedTask.note}
                       onChange={(e: ChangeEvent<HTMLInputElement>) =>
                           handleChange("note", e.target.value)}
                />
                <select
                    value={updatedTask.status}
                    onChange={(e: ChangeEvent<HTMLSelectElement>) =>
                        handleChange("status", e.target.value)}
                >
                    <option value="OPEN">OPEN</option>
                    <option value="IN_PROGRESS">IN_PROGRESS</option>
                    <option value="DONE">DONE</option>
                </select>
                <input type="submit" value="Submit Edit"/>
                <button type="button" onClick={() => onTaskEdit(task)}>Cancel</button>
            </form>
        </div>
    );
};