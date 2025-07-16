import React from 'react';
import menuIcon from '../assets/image/menu-icon.png';

function Header({ onMenuClick}) {
  return (
    <header style={{
      display: 'flex',
      alignItems: 'center', //세로 정렬
      padding: '1rem 2rem',
      backgroundColor: '#ffffff',
      color: 'black',
      boxShadow: '0 4px 4px rgba(0, 0, 0, 0.25)',
      position: 'relative',
      zIndex: 3,
    }}>
      {/* 왼쪽 - 메뉴 버튼 (해당 형식은 JSX 주석임)*/}
      <button
        onClick={onMenuClick}
        style={{
          flex: 1,
          backgroundColor: 'transparent',
          border: 'none',
          padding: '0',
          textAlign: 'left',
          cursor: 'pointer'}}>
          <img src={menuIcon} alt='menu icon'></img>
        </button>

      {/* 가운데 - 제목 */}
      <h1 style={{ flex: 1, textAlign: 'center', margin: 0 }}>SEOUL PUBLIC MAP</h1>

      {/* 오른쪽 - 비어 있는 공간 (좌우 균형용) */}
      <div style={{ flex: 1 }} />
    </header>
  );
}

export default Header;