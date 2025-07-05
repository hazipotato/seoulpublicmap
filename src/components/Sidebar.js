import moreIcon from '../assets/image/more.png';
import addIcon from '../assets/image/add.png';
import hideIcon from '../assets/image/hide.png';

export default function Sidebar({ isOpen, onClose }) {
    return (
      <aside style={{
        width: isOpen ? '22.22vw' /* 또는 '22.22%' (뷰포트 기준 너비)' */ : '0',
        transition: 'width 0.3s',
        overflow: 'hidden',
        backgroundColor: '#ffffff',
        color: 'black',
        height: '100vh',
        padding: isOpen ? '1rem' : '0',
        boxShadow: '0 4px 4px rgba(0, 0, 0, 0.25)',
        position: 'absolute',
        zIndex: 1,
      }}>
        <h1>List</h1>
        <div style={{
          display: 'flex',
          flexDirection: 'row',
          justifyContent: "space-between"
        }}>
          <h4 style={{opacity: 0.5}}>대학로 분좋카</h4>
          <div>
            <button>add</button>
            

            <button
              onClick={onMenuClick}
              style={{
                flex: 1,
                backgroundColor: 'transparent',
                border: 'none',
                padding: '0',
                cursor: 'pointer'}}>
                <img src={moreIcon} alt='menu icon'></img>
            </button>
          </div>
        </div>
        <li>메뉴 1</li>
        <li>메뉴 2</li>
          
        <div style={{ display: 'flex', gap: '8px' }}>
            <button>Add List</button>
            <button>Add Course</button>
        </div>
      </aside>
    );
  }
  