import TaskLists from "./components/TaskLists.tsx";
import SidebarNav from "./components/SidebarNav.tsx";
import Header from "./components/Header.tsx";
import {useCallback, useEffect, useState} from "react";
import {fetchTasks} from "./service/api.ts";
import type { TaskApiResponse } from "./types/taskTypes.ts";
import CreateTask from "./components/CreateTask.tsx";
import EditNoteRoundedIcon from '@mui/icons-material/EditNoteRounded';
import PlaylistRemoveRoundedIcon from '@mui/icons-material/PlaylistRemoveRounded';
import EditTask from "./components/EditTask.tsx";



export default function App() {
    const [tasks, setTasks] = useState<TaskApiResponse[]>([]);
    const [isEditActive, setIsEditActive] = useState<boolean>(false)
    const [editingTaskId, setEditingTaskId] = useState<string | null>(null);

    const fetching = useCallback(async () => {
        fetchTasks()
            .then(setTasks)
            .catch(e => console.log(e));
    }, [])

    useEffect(() => {
        fetching()
            .then();
    },[fetching]);

    /*
     useEffect(() => {
        if (tasks.length > 0) {
            setTaskTitles(tasks.map((task) => task.title));
        }
    }, [tasks]);
     */

    // const [taskTitles, setTaskTitles] = useState<string[]>([])

    const handleTaskCreate = (newTask: TaskApiResponse) => {
        if (newTask) {
            fetching()
                .then();
        }
    };

    const handleClick = (id: string) => {
        setEditingTaskId(id);
        setIsEditActive(true)
    };

    const handleTaskEdit = (updatedData: TaskApiResponse) => {

        if(updatedData){
            const renderedTasks: TaskApiResponse[] = tasks.map((task) => {
                if (task.id === updatedData.id) {
                    return {
                        ...task,
                        title: updatedData.title,
                        note: updatedData.note,
                        status: updatedData.status
                    }
                } else {
                    return task;
                }
            })

            setTasks(renderedTasks)

        }
        setIsEditActive(false)

    }
    console.log(tasks)


  return (
    <>
        <div className='app-main'>
            <Header />
            <div className='app-content'>
                {/*<SidebarNav title={tasks.map(task => task.title)}  />*/}
                <SidebarNav />
                <div>
                    <CreateTask onTaskCreate={handleTaskCreate} />
                    <div>
                        {tasks.map((task: TaskApiResponse) => (
                            <div key={task.id} className='tasklist-app' >
                                <TaskLists
                                    title={task.title}
                                    note={task.note}
                                    status={task.status} />
                                <div className='task-item-buttons'>
                                    {
                                        isEditActive && (editingTaskId === task.id) ?
                                            (<EditTask onTaskEdit={handleTaskEdit}
                                                      isEditActive={editingTaskId === task.id}
                                                      task={task}
                                            />) :
                                            <EditNoteRoundedIcon
                                                onClick={() => handleClick(task.id)} />
                                    }
                                    <PlaylistRemoveRoundedIcon />
                                </div>
                            </div>

                        ))}
                    </div>
                </div>
            </div>
        </div>
    </>
  );
};