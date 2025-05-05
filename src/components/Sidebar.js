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
        <nav>
          <ul>
            <li>메뉴 1</li>
            <li>메뉴 2</li>
          </ul>
        </nav>
        <button>Add List</button>
      </aside>
    );
  }
  