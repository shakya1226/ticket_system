import React from 'react';
import "../assets/LogDisplay.css";

const LogDisplay = ({ logs }) => {
  return (
    <div className="log-display-container">
      <h3>System Logs</h3>
      <div className="logs-container">
        {logs.length === 0 ? (
          <p>No logs yet. Start the system to see activity.</p>
        ) : (
          logs.map((log, index) => <p key={index} className="log-entry">{log}</p>)
        )}
      </div>
    </div>
  );
};

export default LogDisplay;
