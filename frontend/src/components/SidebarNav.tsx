import ListsPanel from "./ListsPanel.tsx";

// interface SidebarNavProps {
//     title: string[];
// }

// export default function SidebarNav( {title}: SidebarNavProps ) {
export default function SidebarNav( ) {
    return (
        <>
          <nav className='sidebar'>
              {/*<ListsPanel title={title} />*/}
              <ListsPanel />
          </nav>
        </>
    )
}