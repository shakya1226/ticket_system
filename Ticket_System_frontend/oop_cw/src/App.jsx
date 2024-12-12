import React, { useState, useEffect } from 'react';
import ConfigurationForm from '../src/components/ConfigurationForm';
import LogDisplay from '../src/components/LogDisplay';
import ControlPanel from '../src/components/ControlPanel';
import TicketDisplay from '../src/components/TicketDisplay';
import '../src/App.css';
import { FaCogs, FaRedo } from 'react-icons/fa';

const App = () => {
  const [config, setConfig] = useState(null);
  const [ticketCount, setTicketCount] = useState(0);
  const [logs, setLogs] = useState([]);
  const [systemStarted, setSystemStarted] = useState(false);
  const [intervalId, setIntervalId] = useState(null);

  const handleConfigSubmit = (configData) => {
    if (configData.totalTickets <= 0) {
      setLogs((prevLogs) => [...prevLogs, 'Error: Total tickets must be greater than zero.']);
      return;
    }
    setConfig(configData);
    setTicketCount(configData.totalTickets);
    setLogs((prevLogs) => [...prevLogs, 'Configuration saved successfully.']);
  };

  const handleStartSystem = () => {
    if (!config || ticketCount <= 0) {
      setLogs((prevLogs) => [...prevLogs, 'Error: Configure tickets before starting the system.']);
      return;
    }
    setSystemStarted(true);
    setLogs((prevLogs) => [...prevLogs, 'System started.']);

    // Start the interval for continuous updates
    const id = setInterval(() => {
      const customerBuyCount = Math.floor(Math.random() * 5) + 1; // Random between 1-5
      const vendorReleaseCount = Math.floor(Math.random() * 5) + 1; // Random between 1-5

      setTicketCount((prevCount) => {
        const newCount = prevCount + vendorReleaseCount - customerBuyCount;
        if (newCount < 0) {
          setLogs((prevLogs) => [...prevLogs, 'No tickets left to process.']);
          return 0;
        }

        setLogs((prevLogs) => [
          ...prevLogs,
          `Vendor released ${vendorReleaseCount} tickets. Customer bought ${customerBuyCount} tickets. Tickets left: ${newCount}`,
        ]);
        return newCount;
      });
    }, config.releaseInterval);

    setIntervalId(id);
  };

  const handleStopSystem = () => {
    setSystemStarted(false);
    clearInterval(intervalId);
    setLogs((prevLogs) => [...prevLogs, 'System stopped.']);
  };

  const handleReset = () => {
    setConfig(null);
    setTicketCount(0);
    setLogs([]);
    setSystemStarted(false);
    clearInterval(intervalId);
    setLogs((prevLogs) => [...prevLogs, 'System reset successfully.']);
  };

  return (
    <div className="App">
      <h1>
        <FaCogs className="app-icon" /> Ticketing System
      </h1>
      {!config ? (
        <ConfigurationForm onConfigSubmit={handleConfigSubmit} />
      ) : (
        <>
          <TicketDisplay ticketCount={ticketCount} />
          <ControlPanel
            systemStarted={systemStarted}
            onStart={handleStartSystem}
            onStop={handleStopSystem}
          />
          <LogDisplay logs={logs} />
          <button className="reset-button" onClick={handleReset}>
            <FaRedo /> Reset System
          </button>
        </>
      )}
    </div>
  );
};

export default App;
