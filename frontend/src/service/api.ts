import axios from "axios";
import type {CreateTaskRequest, TaskApiResponse} from "../types/taskTypes.ts";

const fetchTasks = async () => {
    return await axios.get<TaskApiResponse[]>("/api/todo")
        .then(res => res.data);
}

const createTask = async (data: CreateTaskRequest) => {
    return await axios.post<TaskApiResponse>("/api/todo", data)
        .then(res => res.data);
};

const updateTask = async (data: TaskApiResponse) => {
    return await axios.put(`/api/todo/${data.id}`, data)
        .then(res => res.data)
}

export {
    fetchTasks,
    createTask,
    updateTask
};