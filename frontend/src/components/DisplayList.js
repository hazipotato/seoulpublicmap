import React, { useState, useEffect } from "react";
import hideIcon from "../assets/image/hide.png";
import moreIcon from "../assets/image/more.png";
import addIcon from "../assets/image/add.png";

function DisplayList({ onToggleAdd }) {
  const [isToggled, setIsToggled] = useState(false);
  const onBtnClick = () => {
    console.log("Button clicked");
  };

  const onMoreClick = () => {
    setIsToggled((prev) => !prev); // true <-> false 토글
  };

  const [data, setData] = useState([]);

  useEffect(() => {
    fetch("http://localhost:4000/lists") // json-server endpoint
      .then((res) => res.json())
      .then((json) => setData(json))
      .catch((err) => console.error(err));
  }, []);

  return (
    <>
      <div>
        <button
          id="add"
          onClick={() => {
            onBtnClick();
            onToggleAdd();
          }}
          style={{
            flex: 1,
            backgroundColor: "transparent",
            border: "none",
            cursor: "pointer",
          }}
        >
          <img src={addIcon} alt="add icon"></img>
        </button>
        <button
          id="more"
          onClick={onMoreClick}
          style={{
            flex: 1,
            backgroundColor: "transparent",
            border: "none",
            cursor: "pointer",
          }}
        >
          <img src={isToggled ? moreIcon : hideIcon} alt="more icon" />
        </button>
      </div>
    </>
  );
}

export default DisplayList;
