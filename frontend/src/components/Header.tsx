import { SiGoogletasks } from "react-icons/si";
import { LiaUserCheckSolid } from "react-icons/lia";

export default function Header() {
    return (
          <header className='header'>
              <div className='header-link'>
                  <SiGoogletasks />
                  <h3>Tasks</h3>
              </div>
              <div className='header-user'>
                  <LiaUserCheckSolid />
              </div>
          </header>
    )
}