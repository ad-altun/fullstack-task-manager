import { MdOutlineAdd } from "react-icons/md";

// interface ListsPanelProps {
//     title: string[];
// }

// export default function ListsPanel({title}: ListsPanelProps) {
export default function ListsPanel() {

    return (
        <div>
            {/*<div>*/}
            {/*    {title.map((t, index) => (*/}
            {/*        <div key={index}>{t}</div>*/}
            {/*    ))}*/}
            {/*</div>*/}
            <h4>My Tasks</h4>
            <div className='sidebar-create'>
                <MdOutlineAdd />
                <button type="button">Create new list</button>
            </div>
        </div>
    )
}