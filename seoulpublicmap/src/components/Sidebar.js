import React, { useState } from 'react';
import moreIcon from '../assets/image/more.png';
import addIcon from '../assets/image/add.png';
import hideIcon from '../assets/image/hide.png';

import '../assets/style/style.css';

export default function Sidebar({ isOpen, onClose, onAddListClick}) {
  const [isToggled, setIsToggled] = useState(false);

  const onBtnClick = () => {
    console.log('Button clicked');
  };

  const onMoreClick = () => {
    setIsToggled(prev => !prev); // true <-> false 토글
  };

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
        zIndex: 2,
      }}>
        <h1 style={{marginBottom: 0}}>List</h1>
        <div style={{display: 'flex', justifyContent: 'center'}}>
          <input type="text" class="search-input" placeholder=" 검색창 (임시) " />
        </div>
        
        <div style={{
          display: 'flex',
          flexDirection: 'row',
          justifyContent: "space-between"
        }}>
          <h4 style={{opacity: 0.5, margin: '0'}}>대학로 분좋카</h4>
          <div>
            <button id='add'
              onClick={onBtnClick}
              style={{
                flex: 1,
                backgroundColor: 'transparent',
                border: 'none',
                cursor: 'pointer'}}>
                <img src={addIcon} alt='add icon'></img>
            </button>
            <button id='more'
              onClick={onMoreClick}
              style={{
                flex: 1,
                backgroundColor: 'transparent',
                border: 'none',
                cursor: 'pointer'}}>
                <img src={isToggled ? moreIcon : hideIcon} alt="more icon" />
            </button>
          </div>
        </div>
        <ul>
          <li>청킹</li>
          <li>그루</li>
          <li>타르트앤</li>
          <li>커피한약방</li>
        </ul>
        
          
        <div style={{ display: 'flex', width: '100%', justifyContent: 'space-between' }}>
            <button id='Add List'
              onClick={onAddListClick}
              style={{
                width: '47%',
                aspectRatio: 3 / 1,
                backgroundColor: 'white',
                border: 'none',
                borderRadius: '5px',
                boxShadow: '0 4px 4px rgba(0, 0, 0, 0.25)'}}>
                <img src={addIcon} alt='add icon'></img>Add List
            </button>
            <button id='Add Course'
              style={{
                width: '47%',
                aspectRatio: 3 / 1,
                backgroundColor: 'white',
                border: 'none',
                borderRadius: '5px',
                boxShadow: '0 4px 4px rgba(0, 0, 0, 0.25)'}}>
                <img src={addIcon} alt='add icon'></img>Add Course
            </button>
        </div>
      </aside>
    );
  }
  