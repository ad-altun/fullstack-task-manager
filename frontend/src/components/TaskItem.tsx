import type {TaskStatus} from "../types/taskTypes.ts";

type ListItemProps = {
    title: string,
    note: string,
    status: TaskStatus
}

export default function TaskItem({ title, note, status }: ListItemProps) {
    return (
        <div className='tasks'>
            <div className='task-item-content'>
                {/*<input type="checkbox" name="checkbox" id="checkbox" />*/}
                <div className='task-item'>
                    <p className='task-title'>{title}</p>
                    <p className='task-note'>{note}</p>
                    <p className='task-text'>{status}</p>
                </div>
            </div>
        </div>
    )
}
