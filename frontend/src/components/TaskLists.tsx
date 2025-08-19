import type { TaskStatus} from "../types/taskTypes.ts";
import TaskItem from "./TaskItem.tsx";

type TaskListCompProps = {
    title: string,
    note: string,
    status: TaskStatus
}

export default function TaskLists({ title, note, status }: TaskListCompProps) {

    return (
        <div className='task-list'>
            <TaskItem
                title={title} note={note} status={status} />
        </div>
    )
}