import React from 'react';
import '../assets/ControlPanel.css';
import { FaPlay, FaStop, FaRedo } from 'react-icons/fa';

const ControlPanel = ({ systemStarted, onStart, onStop, onReset }) => {
  return (
    <div className="control-panel-container">
      {!systemStarted ? (
        <button className="start-button" onClick={onStart}>
          <FaPlay /> Start System
        </button>
      ) : (
        <>
          <button className="stop-button" onClick={onStop}>
            <FaStop /> Stop System
          </button>
          
        </>
      )}
    </div>
  );
};

export default ControlPanel;