/* listName: string  createdAt: string, */

export interface TaskApiResponse {
    id: string,
    title: string,
    note: string,
    status: TaskStatus,
}

export type CreateTaskRequest = {
    id: string,
    title: string,
    note: string,
    status: TaskStatus,
    // listName: string,
}

export type TaskProps = {
    title: string,
    note: string,
    status: TaskStatus
}

export type List = {
    id: string,
    name: string,
    isActive: boolean,
    taskCount: number,
}

export interface CreateListRequest {
    name: string;
}

export interface UpdateListRequest {
    name?: string;
}

export interface ListItemProps {
    list: List,
    isActive: boolean,
    taskCount: number,
    onClick: (id: string) => void,
    onEdit: (id: string) => void,
    onDelete: (id: string) => void,
}

export interface TaskItemProps {
    task: TaskApiResponse[],
    onToggle: (id: string) => void,
    onEdit: (id: string, data: Partial<TaskApiResponse>) => void,
    onDelete: (id: string) => void,
}

export type TaskListProps = {
    activeList: List,
    tasks: TaskItemProps[],
    loading: boolean,
    onTaskCreate: (data: CreateTaskRequest) => void,
    onTaskUpdate: (id: string, data: Partial<TaskApiResponse>) => void,
    onTaskDelete: (id: string) => void,
    onTaskToggle: (id: string) => void,
}

export type TaskStatus = "OPEN" | "IN_PROGRESS" | "DONE";

