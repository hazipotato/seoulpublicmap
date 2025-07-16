import React from 'react';

function Search({ }) {
  return (
    <input type="text" placeholder=" 검색창 (임시) "
        style={{
            display: 'flex',
            width: '70%',
            margin: '30px',
            backgroundColor: '#ffffff',
            border: 'none',
            color: 'black',
            boxShadow: '0 4px 4px rgba(0, 0, 0, 0.25)',
            position: 'relative',
            zIndex: 4,}} />
  );
}

export default Search;