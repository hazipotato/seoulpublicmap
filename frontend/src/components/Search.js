import React, { useState } from 'react';

export default function Search({ onSearch }) {
const [value, setValue] = useState('');

const handleSubmit = (e) => {
e.preventDefault();
if (value.trim()) onSearch(value.trim());
};

return (
    <form onSubmit={handleSubmit} style={{ width: '70%' }}>
     <input
        type="text"
        placeholder="  검색창 (임시)  "
        value={value}
        onChange={(e) => setValue(e.target.value)}
        style={{ display: 'flex',
                             width: '70%',
                             margin: '30px',
                             backgroundColor: '#ffffff',
                             border: 'none',
                             color: 'black',
                             boxShadow: '0 4px 4px rgba(0, 0, 0, 0.25)',
                             position: 'relative',
                             zIndex: 4, }}
      />
    </form>
  );
}